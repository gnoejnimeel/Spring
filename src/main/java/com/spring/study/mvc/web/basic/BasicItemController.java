package com.spring.study.mvc.web.basic;

import com.spring.study.mvc.domain.item.Item;
import com.spring.study.mvc.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     *
     테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        //저장
        itemRepository.save(item);
        //저장된 아이템
        model.addAttribute("item", item);
        return "basic/item";
    }

    //ModelAttribute 사용해보기
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        //Model Item을 저장소에 저장
        itemRepository.save(item);
        //ModelAttribute는 Item 객체를 생성하고 model에 지정된 객체를 자동으로 넣어준다! 그래서 따로 model 담아서 보내줄 필요가 없다.
        //model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) { //@ModelAttribute 이름 생략 가능
        //생략하면 model에 저장되는 이름은 클래스명 첫글자만 소문자로 등록된다. Item -> item
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        //@ModelAttribute 자체도 생략 가능하다. 대상 객체는 모델에 자동으로 등록됨
        itemRepository.save(item);
        //뷰 호출
        //웹 브라우저 입장에선 POST + 상품 데이터 전송이 마지막 호출이였음 새로고침하면 또 저장되는 문제!
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        //상품 저중 후에 뷰 템플릿이 아닌 상품 상세 화면으로 리다이렉트를 호출해준다.
        //따라서 마지막에 호출한 내용이 상품 상세 화면인 GET이다.

        //이렇게 더해서 사용하는 것은 URL 인코딩이 안 되기 때문에 위험하다! -> RedirectAttributes 사용할 것
        return "redirect:/basic/items/" + item.getId();
    }

    //고객의 입장에선 안내문구가 없어서 정확히 저장됐는지 알 수 없기 때문에 안내문구 추가함
    //redirectAttributes를 사용하면 URL 인코딩도 해주고 PathVariable, 쿼리 파라미터도 해준다.
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        //저장된 결과
        Item savedItem = itemRepository.save(item);
        //redirectAttributes에 추가함
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        //저장 확인을 위함
        redirectAttributes.addAttribute("status", true);
        //redirectAttributes에 넣은 id가 알아서 치환된다.
        //남는 애들은 쿼리 파라미터로 추가되어 들어간다.
        return "redirect:/basic/items/{itemId}";
        //http://localhost:8080/basic/items/3?status=true
    }

    //수정 페이지로 이동
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    //수정 로직
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        //상품 상세 화면으로 이동하도록 리다이렉트 호출
        return "redirect:/basic/items/{itemId}";
    }
}
