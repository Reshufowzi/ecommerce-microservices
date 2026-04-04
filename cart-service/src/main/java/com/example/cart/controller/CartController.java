@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private RedisTemplate<String, Object> redis;

    @PostMapping("/add")
    public String add(@RequestParam String user, @RequestParam String item) {
        redis.opsForList().rightPush(user, item);
        return "Added";
    }

    @GetMapping("/{user}")
    public List<Object> get(@PathVariable String user) {
        return redis.opsForList().range(user, 0, -1);
    }
}
