package com.nepalfresh.repository.product.impl;

import com.nepalfresh.common.dto.SearchParam;
import com.nepalfresh.common.util.SearchParamUtil;
import com.nepalfresh.entity.Product;
import com.nepalfresh.repository.product.ProductSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.nepalfresh.common.constant.SearchParamConstant.*;

@Repository
@RequiredArgsConstructor
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        StringBuilder query = new StringBuilder("SELECT COUNT(p.id) FROM Product p ");
        query.append(buildWhereClause());

        return (Long) em.createQuery(query.toString())
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("category", SearchParamUtil.getString(searchParam, CATEGORY))
                .setParameter("createdAt", SearchParamUtil.getString(searchParam, CREATED_DATE))
                .setParameter("status", SearchParamUtil.getString(searchParam,STATUS))
                .getSingleResult();
    }

    @Override
    public List<Product> getAll(SearchParam searchParam) {
        StringBuilder query = new StringBuilder("SELECT p FROM Product p ");
        query.append(buildWhereClause());
        query.append("ORDER BY p.createdAt DESC");
        return em.createQuery(query.toString(), Product.class)
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("category", SearchParamUtil.getString(searchParam, CATEGORY))
                .setParameter("createdAt", SearchParamUtil.getString(searchParam, CREATED_DATE))
                .setParameter("status", SearchParamUtil.getString(searchParam,STATUS))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }

    private String buildWhereClause(){
        return " JOIN p.status s " +
                " JOIN p.category c " +
                " WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) AND " +
                " (:createdAt IS NULL OR FUNCTION('DATE_FORMAT', p.createdAt, '%Y-%m-%d') = :createdAt) AND "+
                " (:category IS NULL OR c.name = :category) AND " +
                " (:status IS null OR s.name = :status) ";
    }
}
