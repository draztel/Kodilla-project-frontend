package com.kodilla.project.view;

import com.kodilla.project.domain.dto.FactDto;
import com.kodilla.project.domain.dto.GameDto;
import com.kodilla.project.domain.dto.JokeDto;
import com.kodilla.project.domain.dto.OfferDto;
import com.kodilla.project.service.FactService;
import com.kodilla.project.service.GameService;
import com.kodilla.project.service.JokeService;
import com.kodilla.project.view.form.GameForm;
import com.kodilla.project.view.form.OfferForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("gameView")
public class GameView extends VerticalLayout {
    private GameService gameService = new GameService();
    private FactService factService = new FactService();
    private JokeService jokeService = new JokeService();

    private Grid grid = new Grid<>(GameDto.class);
    private GameForm gameForm = new GameForm();

    private TextField filter = new TextField();
    private Button toMain = new Button("Main page");
    private Button toOffers = new Button("Offers");
    private Button toGames = new Button("Games");
    private Button toMovies = new Button("Movies");
    private Button toUsers = new Button("Users");
    private Button toFact = new Button("Random Fact");
    private Button toJoke = new Button("Random Joke");

    private Dialog randomFact = new Dialog();
    private Dialog randomJoke = new Dialog();

    public GameView() {
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

        filter.setPlaceholder("Filter by name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> getByName());

        HorizontalLayout navigate = new HorizontalLayout(filter, toMain, toOffers, toGames, toMovies, toUsers, toFact, toJoke);
        grid.setColumns("name", "description", "price");

        HorizontalLayout mainContent = new HorizontalLayout(grid, gameForm);
        mainContent.setSizeFull();
        grid.setSizeFull();
        add(navigate, mainContent);
        setSizeFull();
    }

    public void refresh() {
        grid.setItems(gameService.getGames());
    }

    private void getByName() {
        grid.setItems(gameService.getGamesByName(filter.getValue()));
    }

    private String getRandomFact() {
        FactDto factDto = factService.getRandomFact();
        return factDto.getText();
    }

    private String getRandomJoke() {
        JokeDto jokeDto = jokeService.getRandomJoke();
        return jokeDto.getSetup() + "\n" +
                jokeDto.getPunchline();
    }
}
