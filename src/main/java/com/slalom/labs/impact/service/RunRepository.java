package com.slalom.labs.impact.service;

import com.slalom.labs.impact.domain.Run;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by thunt on 8/29/2016.
 */
public interface RunRepository extends PagingAndSortingRepository<Run, String> {
}
