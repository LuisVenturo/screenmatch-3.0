package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    SerieRepository repository;
    public List<SerieDTO> obtenerTodasLasSeries(){
        return repository.findAll().stream()
                .map(serie -> new SerieDTO(serie.getTitulo(), serie.getTotalTemporadas(), serie.getEvaluacion(), serie.getPoster(), serie.getGenero(),
                        serie.getActores(), serie.getSinopsis()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obtenerTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());

    }

    public List<SerieDTO> convierteDatos(List<Serie> serie){
        return serie.stream()
                .map(s -> new SerieDTO(s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(), s.getGenero(),
                        s.getActores(), s.getSinopsis()))
                .collect(Collectors.toList());
    }
}
