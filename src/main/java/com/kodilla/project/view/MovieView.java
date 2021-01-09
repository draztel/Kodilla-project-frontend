package com.kodilla.project.view;

import com.kodilla.project.domain.dto.FactDto;
import com.kodilla.project.domain.dto.GameDto;
import com.kodilla.project.domain.dto.JokeDto;
import com.kodilla.project.domain.dto.MovieDto;
import com.kodilla.project.service.FactService;
import com.kodilla.project.service.GameService;
import com.kodilla.project.service.JokeService;
import com.kodilla.project.service.MovieService;
import com.kodilla.project.view.form.MovieForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("movieView")
public class MovieView extends VerticalLayout {
    private MovieService movieService = new MovieService();
    private FactService factService = new FactService();
    private JokeService jokeService = new JokeService();

    private Grid grid = new Grid<>(MovieDto.class);
    private MovieForm movieForm = new MovieForm();

    private Button toMain = new Button("Main page");
    private Button toOffers = new Button("Offers");
    private Button toGames = new Button("Games");
    private Button toMovies = new Button("Movies");
    private Button toUsers = new Button("Users");
    private Button toFact = new Button("Random Fact");
    private Button toJoke = new Button("Random Joke");

    private Dialog randomFact = new Dialog();
    private Dialog randomJoke = new Dialog();

    public MovieView() {
        toMain.addClickListener(event -> toMain.getUI().ifPresent(ui -> ui.navigate("")));
        toOffers.addClickListener(event -> toOffers.getUI().ifPresent(ui -> ui.navigate("offerView")));
        toGames.addClickListener(event -> toGames.getUI().ifPresent(ui -> ui.navigate("gameView")));
        toMovies.addClickListener(event -> toMovies.getUI().ifPresent(ui -> ui.navigate("movieView")));
        toUsers.addClickListener(event -> toUsers.getUI().ifPresent(ui -> ui.navigate("userView")));
        toFact.addClickListener(event -> randomFact.open());
        toJoke.addClickListener(event -> randomJoke.open());

        randomFact.add(new Text(getRandomFact()));
        randomFact.setCloseOnOutsideClick(true);
        randomJoke.add(new Text(getRandomJoke()));
        randomJoke.setCloseOnOutsideClick(true);

        HorizontalLayout navigate = new HorizontalLayout(toMain, toOffers, toGames, toMovies, toUsers);
        grid.setColumns("name", "description", "author");

        HorizontalLayout mainContent = new HorizontalLayout(grid, movieForm);
        mainContent.setSizeFull();
        grid.setSizeFull();
        add(navigate, mainContent);
        setSizeFull();
    }

    public void refresh() {
        grid.setItems(movieService.getmovies());
    }

    private String getRandomFact() {
        FactDto factDto = factService.getRandomFact();
        return factDto.getText();
    }

    private String getRandomJoke() {
        JokeDto jokeDto = jokeService.getRandomJoke();
        return jokeDto.getPunchline() + "\n" +
                jokeDto.getSetup();
    }
}
