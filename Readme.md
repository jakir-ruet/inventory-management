### Phase 1: Core Scope - We will build these modules first

| Module                | Purpose                                                                                               |
| --------------------- | ----------------------------------------------------------------------------------------------------- |
| **Category**          | Organize products into logical groups (e.g., Electronics, Furniture).                                 |
| **Supplier**          | Manage supplier/vendor information and supplier-product relationships.                                |
| **Buyer (Customer)**  | Manage customer information for sales transactions.                                                   |
| **Warehouse**         | Manage warehouse locations where inventory is stored.                                                 |
| **Product**           | Maintain the product master, including pricing, category, and reorder level.                          |
| **Product Supplier**  | Manage supplier-specific product information such as purchase price, lead time, and primary supplier. |
| **Stock**             | Maintain the current available quantity of each product in each warehouse.                            |
| **Stock Transaction** | Record every inventory movement (Purchase, Sale, Return, Adjustment, Transfer).                       |
| **Purchase**          | Record purchases from suppliers and increase inventory.                                               |
| **Sales**             | Record sales to buyers/customers and decrease inventory.                                              |

> - **Master Data** (Category, Supplier, Buyer, Warehouse, Product)
> - **Operational Data** (Purchase, Sales)
> - **Inventory Control** (Stock, Stock Transactions)

### Phase 2: Main Database Tables

```bash
Inventory Management System Database
====================================

1. Master Tables
----------------
categories
suppliers
buyers
warehouses

2. Product Management
---------------------
products
product_suppliers

3. Inventory Management
-----------------------
stock
stock_transactions

4. Purchase Management
----------------------
purchase_headers
purchase_lines

5. Sales Management
-------------------
sales_headers
sales_lines

6. Security (Later)
-------------------
users
roles
user_roles

7. Audit (Later)
----------------
audit_logs
```

### Relationship summary

```bash
categories          1:N  products
suppliers           1:N  product_suppliers
products            1:N  product_suppliers

warehouses          1:N  stock
products            1:N  stock

warehouses          1:N  stock_transactions
products            1:N  stock_transactions

suppliers           1:N  purchase_headers
purchase_headers    1:N  purchase_lines
products            1:N  purchase_lines

buyers              1:N  sales_headers
sales_headers       1:N  sales_lines
products            1:N  sales_lines
```

### Relationship

| Parent Table     | Child Table        | Relationship | Business Meaning                                 |
| ---------------- | ------------------ | -----------: | ------------------------------------------------ |
| Categories       | Products           |        1 : N | One category contains many products.             |
| Suppliers        | Product_Suppliers  |        1 : N | One supplier can be linked with many products.   |
| Products         | Product_Suppliers  |        1 : N | One product can be supplied by many suppliers.   |
| Buyers           | Sales_Headers      |        1 : N | One buyer/customer can have many sales orders.   |
| Warehouses       | Stock              |        1 : N | One warehouse stores many product stock records. |
| Products         | Stock              |        1 : N | One product can be stored in many warehouses.    |
| Products         | Stock_Transactions |        1 : N | One product has many stock movements.            |
| Warehouses       | Stock_Transactions |        1 : N | One warehouse has many stock movements.          |
| Suppliers        | Purchase_Headers   |        1 : N | One supplier can have many purchase orders.      |
| Purchase_Headers | Purchase_Lines     |        1 : N | One purchase contains multiple items.            |
| Products         | Purchase_Lines     |        1 : N | One product can appear in many purchases.        |
| Buyers           | Sales_Headers      |        1 : N | One buyer/customer can have many sales orders.   |
| Sales_Headers    | Sales_Lines        |        1 : N | One sale contains multiple items.                |
| Products         | Sales_Lines        |        1 : N | One product can appear in many sales.            |

### Phase 3: First Tables - In `database/01-table/01-tables.sql`

### Phase 4: PL-SQL packages-procedures in `database`

| Package         | Responsibility                                   |
| --------------- | ------------------------------------------------ |
| `CATEGORY_PKG`  | CRUD for categories                              |
| `SUPPLIER_PKG`  | CRUD for suppliers                               |
| `BUYER_PKG`     | CRUD for buyers/customers                        |
| `WAREHOUSE_PKG` | CRUD for warehouses                              |
| `PRODUCT_PKG`   | CRUD for products and product-supplier mapping   |
| `INVENTORY_PKG` | Stock balance, stock transactions, adjustments   |
| `PURCHASE_PKG`  | Purchase header/line creation and stock increase |
| `SALES_PKG`     | Sales header/line creation and stock decrease    |

### Phase 5: Design Model

```bash
model
├── Category.java
├── Supplier.java
├── Buyer.java
├── Warehouse.java
├── Product.java
├── ProductSupplier.java
├── Stock.java
├── StockTransaction.java
├── PurchaseHeader.java
├── PurchaseLine.java
├── SalesHeader.java
└── SalesLine.java
```

| Database Column  | Java Field      |
| ---------------- | --------------- |
| `supplier_id`    | `supplierId`    |
| `supplier_name`  | `supplierName`  |
| `contact_person` | `contactPerson` |
| `phone`          | `phone`         |
| `email`          | `email`         |
| `address`        | `address`       |
| `status`         | `status`        |
| `created_at`     | `createdAt`     |
| `updated_at`     | `updatedAt`     |

### Phase 6: DTO Layer, starting with Category DTOs

```bash
dto
├── BuyerCreateRequest.class
├── BuyerResponse.class
├── BuyerUpdateRequest.class
├── CategoryCreateRequest.class
├── CategoryResponse.class
├── CategoryUpdateRequest.class
├── ProductCreateRequest.class
├── ProductResponse.class
├── ProductSupplierCreateRequest.class
├── ProductSupplierResponse.class
├── ProductSupplierUpdateRequest.class
├── ProductUpdateRequest.class
├── PurchaseCreateRequest.class
├── PurchaseLineRequest.class
├── PurchaseLineResponse.class
├── PurchaseResponse.class
├── SalesCreateRequest.class
├── SalesLineRequest.class
├── SalesLineResponse.class
├── SalesResponse.class
├── StockInitializeRequest.class
├── StockResponse.class
├── StockTransactionResponse.class
├── SupplierCreateRequest.class
├── SupplierResponse.class
├── SupplierUpdateRequest.class
├── WarehouseCreateRequest.class
├── WarehouseResponse.class
└── WarehouseUpdateRequest.class
```

### Phase 7: Repository Layer

```bash
mapper
├── CategoryRowMapper.java
├── SupplierRowMapper.java
├── BuyerRowMapper.java
├── WarehouseRowMapper.java
├── ProductRowMapper.java
├── ProductSupplierRowMapper.java
├── StockRowMapper.java
├── StockTransactionRowMapper.java
├── PurchaseHeaderRowMapper.java
├── PurchaseLineRowMapper.java
├── SalesHeaderRowMapper.java
└── SalesLineRowMapper.java
```

```bash
repository
├── BaseRepository.java
├── CategoryRepository.java
├── SupplierRepository.java
├── BuyerRepository.java
├── WarehouseRepository.java
├── ProductRepository.java
├── ProductSupplierRepository.java
├── StockRepository.java
├── StockTransactionRepository.java
├── PurchaseHeaderRepository.java
├── PurchaseLineRepository.java
├── SalesHeaderRepository.java
└── SalesLineRepository.java
```

This repository will call:

```bash
CATEGORY_PKG.ADD_CATEGORY
CATEGORY_PKG.UPDATE_CATEGORY
CATEGORY_PKG.DEACTIVATE_CATEGORY
CATEGORY_PKG.GET_CATEGORY_BY_ID
CATEGORY_PKG.GET_ALL_CATEGORIES
```

### Phase 7: Service Layer

```bash
service
├── CategoryService.java
├── SupplierService.java
├── BuyerService.java
├── WarehouseService.java
├── ProductService.java
├── ProductSupplierService.java
├── StockService.java
├── StockTransactionService.java
├── PurchaseHeaderService.java
├── PurchaseLineService.java
├── SalesHeaderService.java
└── SalesLineService.java
```

**However,**

- If this were a real enterprise application, I would slightly refactor it.
- Instead of `12 services`, I'd use `8 services`, organized by business capability rather than by table.

```bash
service
├── CategoryService.java
├── SupplierService.java
├── BuyerService.java
├── WarehouseService.java
├── ProductService.java
├── InventoryService.java
├── PurchaseService.java
└── SalesService.java
```

**Why?**

Services should represent business processes, not database tables. For example:

`ProductService`

`Handles:`

- Product CRUD
- ProductSupplier CRUD
- Assign supplier to product
- Get suppliers by product

`Instead of:`

- ProductService
- ProductSupplierService

**InventoryService**

`Handles:`

- Stock
- StockTransaction
- Stock adjustment
- Stock inquiry
- Low stock report

`Instead of:`

- StockService
- StockTransactionService

**PurchaseService**

`Handles:`

- PurchaseHeader
- PurchaseLine
- Confirm purchase
- Receive inventory

`Instead of:`

- PurchaseHeaderService
- PurchaseLineService

**SalesService**

`Handles:`

- SalesHeader
- SalesLine
- Confirm sale
- Reduce stock

`Instead of:`

- SalesHeaderService
- SalesLineService

**Recommendation**

I recommend the 8-service architecture because it better reflects your business domains.

```bash
service
├── CategoryService
├── SupplierService
├── BuyerService
├── WarehouseService
├── ProductService
├── InventoryService
├── PurchaseService
└── SalesService
```

### Phase 8: Controller Layer

```bash
controller
├── CategoryController
├── SupplierController
├── BuyerController
├── WarehouseController
├── ProductController
├── InventoryController
├── PurchaseController
└── SalesController
```

### Phase 9: Exception Handling

```bash
exception
├── ApiError.java
├── ErrorResponse.java
├── GlobalExceptionHandler.java
├── ResourceNotFoundException.java
├── DuplicateResourceException.java
├── BusinessException.java
├── DatabaseException.java
├── ValidationException.java
└── UnauthorizedException.java   (Later)
```
