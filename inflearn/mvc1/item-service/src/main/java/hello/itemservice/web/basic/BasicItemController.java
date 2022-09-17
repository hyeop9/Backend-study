package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor    // final이 붙은 멤버변수만 사용해서 생성자를 만들어준다.
public class BasicItemController {

    private final ItemRepository itemRepository;    // 생성자가 1개라 스프링이 @Autowired로 의존관계를 주입해준다.

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    // 상세조회
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 상품 등록 폼
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {

        // @ModelAttribute 사용 시 생략
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);

        itemRepository.save(item);
//      model.addAttribute("item", item); // 자동 추가, 생략 가능
        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {  // Item 을 item으로 바꾸어 줌
        itemRepository.save(item);
        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV4(Item item) {  // 생략 시 자동 @ModelAttribute
        itemRepository.save(item);
        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItemV5(Item item) {  // 생략 시 자동 @ModelAttribute
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    /* 상품 등록 처리 */
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {  // 생략 시 자동 @ModelAttribute
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    /* 상품 수정 처리 */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    // 테스트용 데이터
    @PostConstruct  // 해당 빈의 의존관계가 모두 주입되고 나면 초기화 용도로 호출된다.
    public void init() {

        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
