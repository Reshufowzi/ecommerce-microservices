public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
