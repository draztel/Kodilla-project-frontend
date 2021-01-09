package com.kodilla.project.view.form;

import com.kodilla.project.domain.dto.UserDto;
import com.kodilla.project.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends FormLayout {
    private UserService userService = new UserService();

    private TextField firstname = new TextField("Firstname");
    private TextField lastname = new TextField("Lastname");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");

    private Binder<UserDto> userBinder = new Binder<>(UserDto.class);

    public UserForm() {
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());

        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        add(firstname, lastname, buttons);
        
        userBinder.bindInstanceFields(this);
    }

    private void save() {
        UserDto userDto = userBinder.getBean();
        userService.createUser(userDto);
    }

    private void delete() {
        UserDto userDto = userBinder.getBean();
        userService.deleteUser(userDto.getId());
    }

    private void update() {
        UserDto userDto = userBinder.getBean();
        userService.updateUser(userDto);
    }
}
