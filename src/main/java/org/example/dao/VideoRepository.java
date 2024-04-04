package org.example.dao;

import org.example.bean.VideoBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends CrudRepository<VideoBean, Long> {
}
