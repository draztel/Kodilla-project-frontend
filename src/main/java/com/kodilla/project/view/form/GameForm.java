package com.kodilla.project.view.form;

import com.kodilla.project.domain.dto.GameDto;
import com.kodilla.project.service.GameService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class GameForm extends FormLayout {
    private GameService gameService = new GameService();

    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private NumberField price = new NumberField("Price");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");

    private Binder<GameDto> binder = new Binder<>(GameDto.class);

    public GameForm() {
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        update.addClickListener(event -> update());

        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        add(name, description, price, buttons);

        binder.bindInstanceFields(this);
    }

    public void save() {
        GameDto gameDto = binder.getBean();
        gameService.createGame(gameDto);
    }

    public void delete() {
        GameDto gameDto = binder.getBean();
        gameService.deleteGame(gameDto.getId());
    }

    public void update() {
        GameDto gameDto = binder.getBean();
        gameService.updateGame(gameDto);
    }
}
