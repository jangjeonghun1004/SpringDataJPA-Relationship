# 일대일 단방향

    public class Product { }

    public class ProductDetail {
        @OneToOne
        @JoinColumn(name = "product_id")
        private Product product;
    }

    @OneToOne(optional = false) = inner join
    @OneToOne(optional = true) = left outer join

# 일대일 양방향

    public class Product {
        @OneToOne(mappedBy = "product")
        @ToString.Exclude // = 순환 참조 발생을 방지
        private ProductDetail productDetail;
    }

    public class ProductDetail {
        @OneToOne
        @JoinColumn(name = "product_id")
        private Product product;
    }

    mappedBy = 어떤 객체가 주인인지 표시하는 속성
             = 해당 설정은 필드를 컬럼으로 인식하지 않는다.

# 다대일 단방향

    public class Product {
        @ManyToOne
        @ToString.Exclude
        @JoinColumn(name = "provider_id")
        private Provider provider;
    }

# 다대일 양방향

    public class Product {
        @ManyToOne
        @ToString.Exclude
        @JoinColumn(name = "provider_id")
        private Provider provider;
    }

    public class Provider {
        @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
        @ToString.Exclude
        private List<Product> products = new ArrayList<Product>();
    }

# 일대다 단방향

    public class Product { }

    public class Category {
        @OneToMany(fetch = FetchType.EAGER)
        @JoinColumn(name = "category_id")
        private List<Product> products = new ArrayList<Product>();
    }

# 다대다 단방향

    public class Product { }

    public class Producer {
        @ManyToMany
        @ToString.Exclude
        private List<Product> products = new ArrayList<Product>();
    }

# 다대다 양방향

    public class Product { 
        @ManyToMany
        @ToString.Exclude
        private List<Producer> producers = new ArrayList<>();
    }

    public class Producer {
        @ManyToMany
        @ToString.Exclude
        private List<Product> products = new ArrayList<Product>();
    }
    
# 영속성 전이

    영속성 전이란? 
    특정 엔티티의 상태가 변경이 될때 해당 엔티티와 연관된
    다른 엔티티의 영속성에도 영향을 미쳐 상태를 변경하는 것을 의미합니다.

    cascade = CascadeType.PERSIST

# 고아 객체

    고아(orphan) 객체란?
    부모 엔티티와 연관관계가 끊어진 엔티티를 의미합니다.

    orphanRemoval = true
    
    






    
