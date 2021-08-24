package br.com.apssystem.algafood.domain.service;

import java.util.List;

import br.com.apssystem.algafood.domain.enums.StatusPedido;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.domain.model.Pedido;
import br.com.apssystem.algafood.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoRepository pedidoRepository;

    @Transactional
    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido atualizar(Pedido pedido) {
        if (null != pedido.getId()) {
            pedido.setStatus(StatusPedido.CRIADO);
        }
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            pedidoRepository.deleteById(id);
            pedidoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException("Pedido", id);
        } catch (DataIntegrityViolationException e) {
            throw new RegistroEmUsoException("Pedido", id);
        }
    }

    public Pedido buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Pedido", id)
        );
        return pedido;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

}
