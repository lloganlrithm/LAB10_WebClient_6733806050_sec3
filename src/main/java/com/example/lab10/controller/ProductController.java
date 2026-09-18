package com.example.lab10.controller;

import com.example.lab10.model.Product;
import com.example.lab10.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * ProductController — Reactive REST Controller
 *
 * ✅ @RestController, @RequestMapping, Constructor Injection ครบแล้ว
 * ✅ endpoint GET /products/{id} ทำเสร็จแล้วเป็นตัวอย่าง (30%)
 * ❌ TODO: เติม method body ของ endpoint ที่เหลือ (70%)
 *
 * Endpoints ที่ต้องทำทั้งหมด:
 *   GET    /products          → Flux<Product>   (ดึงทั้งหมด)
 *   GET    /products/{id}     → Mono<Product>   ✅ ตัวอย่างทำแล้ว
 *   POST   /products          → Mono<Product>   (บันทึก)
 *   DELETE /products/{id}     → Mono<Void>      (ลบ)
 *   GET    /products/category/{cat} → Flux<Product> (กรอง)
 *   GET    /products/{id}/price    → Mono<Double>   (ราคาหลังลด)
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    // ── Constructor Injection (DIP — SOLID) ─────────────
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ══════════════════════════════════════════════════════
    // ✅ ตัวอย่างที่ทำเสร็จแล้ว
    // ══════════════════════════════════════════════════════

    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable String id) {
        return service.getById(id);
    }

    // ══════════════════════════════════════════════════════
    // ✅ TODO ที่เติมแล้ว
    // ══════════════════════════════════════════════════════

    @GetMapping
    public Flux<Product> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return service.save(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    @GetMapping("/category/{category}")
    public Flux<Product> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    @GetMapping("/{id}/price")
    public Mono<Double> getDiscountedPrice(@PathVariable String id) {
        return service.getDiscountedPrice(id);
    }
}