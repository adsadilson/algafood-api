package br.com.apssystem.algafood.domain.service;

import java.util.List;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {

    private static final String NOMECLASS = "Pedido";
    private PedidoRepository pedidoRepository;
    private CidadeService cidadeService;
    private UsuarioService usuarioService;
    private RestauranteService restauranteService;
    private FormaPagtoService formaPagtoService;
    private ProdutoService produtoService;

    @Transactional
    public Pedido salvar(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);
        pedido.setTaxaFrete(pedido.getRestaurante().getFrete());
        pedido.calcularValorTotal();
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido atualizar(Pedido pedido) {
        return salvar(pedido);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            pedidoRepository.deleteById(id);
            pedidoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(NOMECLASS, id);
        } catch (DataIntegrityViolationException e) {
            throw new RegistroEmUsoException(NOMECLASS, id);
        }
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(NOMECLASS, id)
        );
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.listarTodosPedidos();
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cidadeService.buscarPorId(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = usuarioService.buscarPorId(pedido.getCliente().getId());
        Restaurante restaurante = restauranteService.buscarPorId(pedido.getRestaurante().getId());
        FormaPagto formaPagto = formaPagtoService.buscarPorId(pedido.getFormaPagto().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagto(formaPagto);

        if (restaurante.naoAceitaFormaPagamento(formaPagto)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagto.getDescricao()));
        }
        pedido.inclusao();
    }

    private void validarItens(Pedido obj) {
        obj.getItens().forEach(item -> {
            Produto produto = produtoService.buscarPorIdAndRestaurante(
                    obj.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(obj);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }


}
