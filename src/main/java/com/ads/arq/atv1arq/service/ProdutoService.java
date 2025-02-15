package com.ads.arq.atv1arq.service;

import com.ads.arq.atv1arq.model.ProdutoModel;
import com.ads.arq.atv1arq.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    public ProdutoRepository produtoRepository;

    public void salvar(ProdutoModel produto) {
        produtoRepository.save(produto);
    }

    public List<ProdutoModel> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public ProdutoModel atualizar(ProdutoModel produto) {
        if (produto.getId() == null) {
            throw new IllegalArgumentException("ID precisa ser informado para a atualizacao");
        }
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}
