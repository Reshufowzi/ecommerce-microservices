@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @PostMapping
    public Product add(@RequestBody Product p) {
        return repo.save(p);
    }

    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }
}
