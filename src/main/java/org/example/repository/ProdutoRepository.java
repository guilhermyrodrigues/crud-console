package org.example.repository;

import org.example.model.ProdutoModel;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProdutoRepository{

    public void criar(ProdutoModel produto) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(produto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<ProdutoModel> buscarTodos() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("FROM produtos", ProdutoModel.class).list();
        }
    }

    public ProdutoModel buscorPorId(Long id) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(ProdutoModel.class, id);
        }
    }

}
