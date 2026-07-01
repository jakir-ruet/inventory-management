-- Test stock increase
BEGIN
    inventory_pkg.increase_stock(
        p_product_id       => 1,
        p_warehouse_id     => 1,
        p_quantity         => 50,
        p_transaction_type => 'PURCHASE',
        p_reference_no     => 'INIT-001',
        p_remarks          => 'Initial stock entry'
    );
END;
/

-- Test stock decrease
BEGIN
    inventory_pkg.decrease_stock(
        p_product_id       => 1,
        p_warehouse_id     => 1,
        p_quantity         => 5,
        p_transaction_type => 'SALE',
        p_reference_no     => 'SALE-001',
        p_remarks          => 'Sample sale issue'
    );
END;
/

SELECT * FROM stock;
SELECT * FROM stock_transactions;
