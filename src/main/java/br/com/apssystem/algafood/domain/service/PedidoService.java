package br.com.apssystem.algafood.domain.service;

import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.domain.model.*;
import br.com.apssystem.algafood.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void excluir(String codigo) {
        var p = buscarPorCodigo(codigo);
        try {
            pedidoRepository.deleteById(p.getId());
            pedidoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(NOMECLASS, p.getId());
        } catch (DataIntegrityViolationException e) {
            throw new RegistroEmUsoException(NOMECLASS,p.getId());
        }
    }

    public Pedido buscarPorCodigo(String codigo) {
        return (Pedido) pedidoRepository.findByCodigo(codigo).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Pedido não encontrado com o código "+codigo)
        );
    }

    public Page<Pedido> pesquisar(Specification<Pedido> filter, Pageable pageable) {
        return pedidoRepository.findAll(filter, pageable);
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
