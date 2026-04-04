@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository repo;

    @PostMapping
    public Payment pay(@RequestBody Payment p) {
        p.setStatus("SUCCESS");
        return repo.save(p);
    }

    @GetMapping
    public List<Payment> getAll() {
        return repo.findAll();
    }
}
