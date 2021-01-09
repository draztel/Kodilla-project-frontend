package com.kodilla.project.view;

import com.kodilla.project.domain.dto.FactDto;
import com.kodilla.project.domain.dto.JokeDto;
import com.kodilla.project.service.FactService;
import com.kodilla.project.service.JokeService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private FactService factService = new FactService();
    private JokeService jokeService = new JokeService();

    private Button toMain = new Button("Main page");
    private Button toOffers = new Button("Offers");
    private Button toGames = new Button("Games");
    private Button toMovies = new Button("Movies");
    private Button toUsers = new Button("Users");
    private Button toFact = new Button("Random Fact");
    private Button toJoke = new Button("Random Joke");

    private Dialog randomFact = new Dialog();
    private Dialog randomJoke = new Dialog();
    private Label createdBy = new Label("Created by Piotr Wojtkiewicz");

    public MainView() {
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

        VerticalLayout buttons = new VerticalLayout(toMain, toOffers, toGames, toMovies, toUsers, toFact, toJoke);
        VerticalLayout creatorInfo = new VerticalLayout(createdBy);
        creatorInfo.setAlignItems(Alignment.CENTER);
        buttons.setAlignItems(Alignment.CENTER);
        add(buttons, creatorInfo);
        setSizeFull();
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
