export interface Catalog {
    id: string;
    category: string;
    image: string;
}

export interface Product {
    id: string;
    name: string;
    description: string;
    price: string;
    quantity: number;
    catalogId: string;
    image: string;
}