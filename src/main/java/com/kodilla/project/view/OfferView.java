package com.kodilla.project.view;

import com.kodilla.project.domain.dto.FactDto;
import com.kodilla.project.domain.dto.JokeDto;
import com.kodilla.project.domain.dto.OfferDto;
import com.kodilla.project.service.FactService;
import com.kodilla.project.service.JokeService;
import com.kodilla.project.service.OfferService;
import com.kodilla.project.view.form.OfferForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("offerView")
public class OfferView extends VerticalLayout {
    private OfferService offerService = OfferService.getInstance();
    private FactService factService = new FactService();
    private JokeService jokeService = new JokeService();

    private Grid grid = new Grid<>(OfferDto.class);
    private OfferForm offerForm = new OfferForm(this);

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

    public OfferView() {
        grid.asSingleSelect().addValueChangeListener(event -> offerForm.setOffer((OfferDto) grid.asSingleSelect().getValue()));
        toMain.addClickListener(event -> toMain.getUI().ifPresent(ui -> ui.navigate("")));
        toOffers.addClickListener(event -> toOffers.getUI().ifPresent(ui -> ui.navigate("offerView")));
        toGames.addClickListener(event -> toGames.getUI().ifPresent(ui -> ui.navigate("gameView")));
        toMovies.addClickListener(event -> toMovies.getUI().ifPresent(ui -> ui.navigate("movieView")));
        toUsers.addClickListener(event -> toUsers.getUI().ifPresent(ui -> ui.navigate("userView")));
        toFact.addClickListener(event -> {
            randomFact.removeAll();
            randomFact.add(new Text(getRandomFact()));
            randomFact.open();
        });
        toJoke.addClickListener(event -> {
            randomJoke.removeAll();
            randomJoke.add(new Text(getRandomJoke()));
            randomJoke.open();
        });
        randomFact.setCloseOnOutsideClick(true);
        randomJoke.setCloseOnOutsideClick(true);

        filter.setPlaceholder("Filter by name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> {
            if(filter.getValue().length() >= 1) {
                getByName();
            } else {
                refresh();
            }
        });

        HorizontalLayout navigate = new HorizontalLayout(filter, toMain, toOffers, toGames, toMovies, toUsers, toFact, toJoke);
        grid.setColumns("name", "description", "price");

        HorizontalLayout mainContent = new HorizontalLayout(grid, offerForm);
        mainContent.setSizeFull();
        grid.setSizeFull();
        add(navigate, mainContent);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(offerService.getOffers());
    }

    private void getByName() {
        grid.setItems(offerService.getOffersByName(filter.getValue()));
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
