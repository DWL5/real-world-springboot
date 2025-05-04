package org.example.realwordspringboot.service.tag;

import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.repository.TagRepository;
import org.example.realwordspringboot.repository.entity.TagEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Set<String> getTags() {
        var entities = tagRepository.findAll();
        return entities.stream()
                .map(TagEntity::getName)
                .collect(Collectors.toSet());
    }
}
