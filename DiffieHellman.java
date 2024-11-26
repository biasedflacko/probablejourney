public class DiffieHellman {
    public static void main(String args[]) {
        int p = 23;  // publicly known (prime number)
        int g = 5;   // publicly known (primitive root)
        int x = 4;   // only Alice knows this secret
        int y = 3;   // only Bob knows this secret

        // Alice computes (g^x) % p
        int aliceSends = modExp(g, x, p);

        // Bob computes (Alice's value ^ y) % p
        int bobComputes = modExp(aliceSends, y, p);

        // Bob computes (g^y) % p
        int bobSends = modExp(g, y, p);

        // Alice computes (Bob's value ^ x) % p
        int aliceComputes = modExp(bobSends, x, p);

        // Shared secret computed directly from (g^(x * y)) % p
        int sharedSecret = modExp(g, x * y, p);

        System.out.println("Simulation of Diffie-Hellman Key Exchange Algorithm");
        System.out.println("-------------------------------------------");
        System.out.println("Alice Sends : " + aliceSends);
        System.out.println("Bob Computes : " + bobComputes);
        System.out.println("Bob Sends : " + bobSends);
        System.out.println("Alice Computes : " + aliceComputes);
        System.out.println("Shared Secret : " + sharedSecret);

        // shared secrets should match and equality is transitive
        if (aliceComputes == sharedSecret && bobComputes == sharedSecret) {
            System.out.println("Success: Shared Secrets Match! " + sharedSecret);
        } else {
            System.out.println("Error: Shared Secrets do not Match");
        }
    }

    // Function for modular exponentiation: (base^exp) % mod
    public static int modExp(int base, int exp, int mod) {
        int result = 1;
        base = base % mod; // To handle base larger than mod
        while (exp > 0) {
            // If exp is odd, multiply base with result
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            // Divide exp by 2 and square the base
            exp = exp / 2;
            base = (base * base) % mod;
        }
        return result;
    }
}
