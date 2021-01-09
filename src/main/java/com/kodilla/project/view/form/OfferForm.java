package com.kodilla.project.view.form;

import com.kodilla.project.domain.dto.OfferDto;
import com.kodilla.project.service.OfferService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class OfferForm extends FormLayout {
    private OfferService service = OfferService.getInstance();

    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private NumberField price = new NumberField("Price");

    private Button save = new Button("Save");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");

    private Binder<OfferDto> binder = new Binder<>(OfferDto.class);

    public OfferForm() {
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());

        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        add(name, description, price, buttons);

        binder.bindInstanceFields(this);
    }

    private void save() {
        OfferDto offerDto = binder.getBean();
        service.createOffer(offerDto);
    }

    private void update() {
        OfferDto offerDto = binder.getBean();
        service.updateOffer(offerDto);
    }

    private void delete() {
        OfferDto offerDto = binder.getBean();
        service.deleteOffer(offerDto.getId());
    }

    public void setOffer(OfferDto offerDto) {
        binder.setBean(offerDto);

        if(offerDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}
