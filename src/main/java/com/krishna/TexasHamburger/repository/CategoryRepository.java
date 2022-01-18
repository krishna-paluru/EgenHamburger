package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public void deleteByCategoryId(Long id);
    Category getCategoryByCategoryName(String name);
}
