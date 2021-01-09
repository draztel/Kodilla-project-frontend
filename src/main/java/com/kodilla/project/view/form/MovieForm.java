package com.kodilla.project.view.form;

import com.kodilla.project.domain.dto.GameDto;
import com.kodilla.project.domain.dto.MovieDto;
import com.kodilla.project.service.GameService;
import com.kodilla.project.service.MovieService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class MovieForm extends FormLayout {
    private MovieService movieService = new MovieService();

    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private TextField author = new TextField("Author");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");

    private Binder<MovieDto> binder = new Binder<>(MovieDto.class);

    public MovieForm() {
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        update.addClickListener(event -> update());

        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        add(name, description, author, buttons);

        binder.bindInstanceFields(this);
    }

    public void save() {
        MovieDto movieDto = binder.getBean();
        movieService.createMovie(movieDto);
    }

    public void update() {
        MovieDto movieDto = binder.getBean();
        movieService.updateMovie(movieDto);
    }

    public void delete() {
        MovieDto movieDto = binder.getBean();
        movieService.deleteMovie(movieDto.getId());
    }
}
