package org.example.service;

import org.example.model.ProdutoModel;
import org.example.repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {

    public ProdutoRepository produtoRepository = new ProdutoRepository();

    public void salvar(ProdutoModel produto) {
        produtoRepository.criar(produto);
    }

    public List<ProdutoModel> buscarTodos() {
        return produtoRepository.buscarTodos();
    }

    public ProdutoModel buscarPorId(Long id) {
        return produtoRepository.buscorPorId(id);
    }
}
