package com.uni.innovationConnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uni.innovationConnect.dto.IdeaDTO;
import com.uni.innovationConnect.repository.IdeaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdeaService {
    private final IdeaRepository ideaRepository;

    public List<IdeaDTO> getAllIdeas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllIdeas'");
    }

    public IdeaDTO getIdeaById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdeaById'");
    }

    public IdeaDTO createIdea(IdeaDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createIdea'");
    }

    public IdeaDTO editIdea(Long id, IdeaDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editIdea'");
    }

    public void deleteIdea(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteIdea'");
    }
}
