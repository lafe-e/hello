package com.sud.tab.repository;

import com.sud.tab.model.Propose;
import com.sud.tab.model.TabShow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposeRepository extends JpaRepository<Propose,String> {
    void deleteById(String id);

    List<Propose> findAll();
}
