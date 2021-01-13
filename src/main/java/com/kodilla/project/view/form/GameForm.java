package com.kodilla.project.view.form;

import com.kodilla.project.domain.dto.GameDto;
import com.kodilla.project.service.GameService;
import com.kodilla.project.view.GameView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class GameForm extends FormLayout {
    private GameView gameView;
    private GameService gameService = new GameService();
    private GameDto gameDto = new GameDto();

    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private NumberField price = new NumberField("Price");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");

    private Binder<GameDto> binder = new Binder<>(GameDto.class);

    public GameForm(GameView gameView) {
        this.gameView = gameView;
        save.addClickListener(event -> {
            save();
            setGame(gameDto);
        });
        delete.addClickListener(event -> {
            delete();
            setGame(gameDto);
        });
        update.addClickListener(event -> {
            update();
            setGame(gameDto);
        });

        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        add(name, description, price, buttons);

        binder.bindInstanceFields(this);
        setGame(gameDto);
    }

    public void setGame(GameDto gameDto) {
        binder.setBean(gameDto);
    }

    public void save() {
        GameDto gameDto = binder.getBean();
        gameService.createGame(gameDto);
        gameView.refresh();
    }

    public void delete() {
        GameDto gameDto = binder.getBean();
        gameService.deleteGame(gameDto.getId());
        gameView.refresh();
    }

    public void update() {
        GameDto gameDto = binder.getBean();
        gameService.updateGame(gameDto);
        gameView.refresh();
    }
}
