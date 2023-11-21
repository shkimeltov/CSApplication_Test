package com.example.jpatestapp.Service;

import com.example.jpatestapp.Controller.BoardSpecification;
import com.example.jpatestapp.Entity.Board;
import com.example.jpatestapp.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board getContent(int idx) {
        Optional<Board> optionalContent = boardRepository.findById(idx);
        return optionalContent.get();
    }

    public Page<Board> getAllContents(Pageable pageable, String searchType, String keyword) {
        Specification<Board> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(BoardSpecification.equalsDeleteYn("N"));
        if(!keyword.isEmpty()){
            if(searchType.equals("email")){
                spec = spec.and(BoardSpecification.equalsWriter(keyword));
            }else if(searchType.equals("title")){
                spec = spec.and(BoardSpecification.equalsTitle(keyword));
            }else if(searchType.equals("texts")){
                spec = spec.and(BoardSpecification.equalsTexts(keyword));
            }
        }
        return boardRepository.findAll(spec, pageable);
    }

    public void writeContent(Board content) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        content.setRegisterDate(formattedDate);
        content.setUpdateDate(formattedDate);
        boardRepository.save(content);
    }

    public void updateContent(int idx, Board content) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        content.setUpdateDate(formattedDate);
        boardRepository.save(content);
    }

    public void deleteContent(int idx) {
        Board content = boardRepository.findById(idx).get();
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        content.setUpdateDate(formattedDate);
        content.setDeleteYn("Y");
        boardRepository.save(content);
    }
}
