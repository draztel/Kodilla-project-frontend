package com.kodilla.project.view.form;

import com.kodilla.project.domain.dto.MovieDto;
import com.kodilla.project.service.MovieService;
import com.kodilla.project.view.MovieView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class MovieForm extends FormLayout {
    private MovieView movieView;
    private MovieService movieService = new MovieService();
    private MovieDto movieDto = new MovieDto();

    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private TextField author = new TextField("Author");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");

    private Binder<MovieDto> binder = new Binder<>(MovieDto.class);

    public MovieForm(MovieView movieView) {
        this.movieView = movieView;
        save.addClickListener(event -> {
            save();
            setMovie(movieDto);
        });
        delete.addClickListener(event -> {
            delete();
            setMovie(movieDto);
        });
        update.addClickListener(event -> {
            update();
            setMovie(movieDto);
        });

        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        add(name, description, author, buttons);

        binder.bindInstanceFields(this);
        setMovie(movieDto);
    }

    public void save() {
        MovieDto movieDto = binder.getBean();
        movieService.createMovie(movieDto);
        movieView.refresh();
    }

    public void update() {
        MovieDto movieDto = binder.getBean();
        movieService.updateMovie(movieDto);
        movieView.refresh();
    }

    public void delete() {
        MovieDto movieDto = binder.getBean();
        movieService.deleteMovie(movieDto.getId());
        movieView.refresh();
    }
    public void setMovie(MovieDto movieDto) {
        binder.setBean(movieDto);
    }
}
