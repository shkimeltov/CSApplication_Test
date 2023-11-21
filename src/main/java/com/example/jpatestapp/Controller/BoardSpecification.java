package com.example.jpatestapp.Controller;

import com.example.jpatestapp.Entity.Board;
import org.springframework.data.jpa.domain.Specification;

public class BoardSpecification {
    public static Specification<Board> equalsDeleteYn(String deleteYn) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("deleteYn"),deleteYn);
    }
    public static Specification<Board> equalsTitle(String keyword) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("title"),"%" + keyword + "%");
    }

    public static Specification<Board> equalsTexts(String keyword) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("texts"),"%" + keyword + "%");
    }
    public static Specification<Board> equalsWriter(String keyword) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("writer"),"%" + keyword + "%");
    }

}
