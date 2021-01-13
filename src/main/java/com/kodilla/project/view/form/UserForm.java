package com.kodilla.project.view.form;

import com.kodilla.project.domain.dto.UserDto;
import com.kodilla.project.service.UserService;
import com.kodilla.project.view.UserView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends FormLayout {
    private UserView userView;
    private UserService userService = new UserService();
    private UserDto userDto = new UserDto();

    private TextField firstname = new TextField("Firstname");
    private TextField lastname = new TextField("Lastname");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");

    private Binder<UserDto> binder = new Binder<>(UserDto.class);

    public UserForm(UserView userView) {
        this.userView = userView;
        save.addClickListener(event -> {
            save();
            setUser(userDto);
        });
        update.addClickListener(event -> {
            update();
            setUser(userDto);
        });
        delete.addClickListener(event -> {
            delete();
            setUser(userDto);
        });

        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        add(firstname, lastname, buttons);
        
        binder.bindInstanceFields(this);
        setUser(userDto);
    }

    private void save() {
        UserDto userDto = binder.getBean();
        userService.createUser(userDto);
        userView.refresh();
    }

    private void delete() {
        UserDto userDto = binder.getBean();
        userService.deleteUser(userDto.getId());
        userView.refresh();
    }

    private void update() {
        UserDto userDto = binder.getBean();
        userService.updateUser(userDto);
        userView.refresh();
    }

    public void setUser(UserDto userDto) {
        binder.setBean(userDto);
    }
}
