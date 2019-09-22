package com.sud.tab.repository;

import com.sud.tab.model.TabShow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabShowRepository extends JpaRepository<TabShow,String> {
    void deleteById(String id);

    Page<TabShow> findByGrade(Integer grade, Pageable pageable);
}
