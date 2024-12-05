export interface Catalog {
    id: string;
    category: string;
    image: string;
}

export interface Product {
    productId: string;
    name: string;
    description: string;
    price: number;
    quantity: number;
    catalogId: string;
    image: string;
}

export interface User {
    email: string;
    password?: string;
    name?: string;
}

export interface AuthResponse {
    success: boolean;
    message: string;
    user?: User;
}