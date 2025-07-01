package com.spring.study.thymeleaf.form;

import com.spring.study.thymeleaf.form.domain.DeliveryCode;
import com.spring.study.thymeleaf.form.domain.Form;
import com.spring.study.thymeleaf.form.domain.FormRepository;
import com.spring.study.thymeleaf.form.domain.FormType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class FormItemController {

    private final FormRepository itemRepository;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        return regions;
    }

    @ModelAttribute("itemTypes")
    public FormType[] itemTypes() {
        return FormType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
        return deliveryCodes;
    }

    @GetMapping
    public String items(Model model) {
        List<Form> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "thymeleaf/form/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Form item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "thymeleaf/form/item";
    }

    /**
     * 추가 저장
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        //빈 객체 전달
        model.addAttribute("item", new Form());
        return "thymeleaf/form/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Form item, RedirectAttributes redirectAttributes) {
        log.info("item.open={}", item.getOpen());
        log.info("item.regions={}", item.getRegions());
        log.info("item.itemType={}", item.getItemType());
        Form savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/form/items/{itemId}";
    }

    /**
     * 수정
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Form item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "thymeleaf/form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Form item) {
        itemRepository.update(itemId, item);
        return "redirect:/form/items/{itemId}";
    }
}
