package com.spring.study.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV3 {

    private final ValidationItemRepository itemRepository;

    private final ItemValidator itemValidator;

    @InitBinder //해당 컨트롤러에만 적용
    public void init(WebDataBinder dataBinder) {
        log.info("init binder {}", dataBinder);
        dataBinder.addValidators(itemValidator);
    }

    @GetMapping
    public String items(Model model) {
        List<ValidationItem> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v3/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        ValidationItem item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v3/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new ValidationItem());
        return "validation/v3/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@ModelAttribute ValidationItem item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //필드 오류
        if(!StringUtils.hasText(item.getItemName())) {
            //오브젝트 이름, 필드이름, 오류 메시지
            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
        }
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                //필드를 넘어선 오류라면 ObjectError 객체 생성 -> 오브젝트 이름, 오류 메시지
                bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        if(bindingResult.hasErrors()) {
            return "validation/v3/addForm";
        }

        //검증 성공
        ValidationItem savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v3/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute ValidationItem item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //필드 오류
        if(!StringUtils.hasText(item.getItemName())) {
            //오브젝트 이름, 필드이름, 오류 메시지
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수입니다."));
        }
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                //필드를 넘어선 오류라면 ObjectError 객체 생성 -> 오브젝트 이름, 오류 메시지
                bindingResult.addError(new ObjectError("item", null, null, "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        if(bindingResult.hasErrors()) {
            return "validation/v3/addForm";
        }

        //검증 성공
        ValidationItem savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v3/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV3(@ModelAttribute ValidationItem item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //필드 오류
        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");
//        if(!StringUtils.hasText(item.getItemName())) {
//            //오브젝트 이름, 필드이름, 오류 메시지
//            bindingResult.rejectValue("itemName", "required");
//        }
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                //필드를 넘어선 오류라면 ObjectError 객체 생성 -> 오브젝트 이름, 오류 메시지
                bindingResult.reject("totalPriceMin",  new Object[]{10000, resultPrice}, null);
            }
        }

        if(bindingResult.hasErrors()) {
            return "validation/v3/addForm";
        }

        //검증 성공
        ValidationItem savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v3/items/{itemId}";
    }

    //    @PostMapping("/add")
    public String addItemV4(@ModelAttribute ValidationItem item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.rejectValue("itemName", "required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.rejectValue("price", "range", new Object[]{1000, 10000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            return "validation/v3/addForm";
        }

        //성공 로직
        ValidationItem savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v3/items/{itemId}";
    }

    //    @PostMapping("/add")
    public String addItemV5(@ModelAttribute ValidationItem item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //스프링 빈으로 주입 받아서 직접 호출
        itemValidator.validate(item, bindingResult);

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            return "validation/v3/addForm";
        }

        //성공 로직
        ValidationItem savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v3/items/{itemId}";
    }

    //    @PostMapping("/add")
    public String addItemV6(@Validated @ModelAttribute ValidationItem item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //validator를 직접 호출하는 부분이 사라지고 검증 대상 앞에 @Validated를 붙였다.

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            return "validation/v3/addForm";
        }

        //성공 로직
        ValidationItem savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v3/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        ValidationItem item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v3/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute ValidationItem item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v3/items/{itemId}";
    }

}

