package br.com.apssystem.algafood.api.mapper;


import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.core.jackson.PageModel;

@Component
public class PageModelMapper<T, S> {

    @Autowired
    ModelMapper modelMapper;


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public PageModel<S> toCollectionModel(Page page, Class<S> type) {
        PageModel<S> pageModel = new PageModel<>();
        Collection<T> domainElementsList = (Collection<T>) page.getContent();
        pageModel.setContent(
                domainElementsList.stream()
                        .map(object -> modelMapper.map(object, type))
                        .collect(Collectors.toList()));
        pageModel.setNumber(page.getNumber());
        pageModel.setSize(page.getSize());
        pageModel.setTotalElements(page.getTotalElements());
        pageModel.setTotalPages(page.getTotalPages());
        return pageModel;
    }

}
