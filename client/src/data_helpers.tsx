
import bathroomToiletries from './images/1.jpg';
import cleaningTools from './images/2.jpg';
import cleaningProducts from './images/3.jpg';
import productOne from './images/001.jpg';
import productTwo from './images/002.jpg';
import productThree from './images/003.jpg';
import productFour from './images/004.jpg';
import productFive from './images/005.jpg';
import productSix from './images/006.jpg';
import productSeven from './images/007.jpg';
import productEight from './images/008.jpg';
import productNine from './images/009.jpg';
import productTen from './images/010.jpg';
import productEleven from './images/011.jpg';
import productTwelve from './images/012.jpg';
import productThirteen from './images/013.jpg';
import productFourteen from './images/014.jpg';
import productFifteen from './images/015.jpg';
import productSixteen from './images/016.jpg';
import productSeventeen from './images/017.jpg';
import productEighteen from './images/018.jpg';
import productNineteen from './images/019.jpg';
import productTwenty from './images/020.jpg';
import { ProductData } from './components/product_preview';
// localhost:3000/
// list of catalogs for the home page
// I have a db in .csv format and I need to send data from this db to the front end and the front end
// gets the data through axios. I need to send the data in JSON format.

// 4 diff types of users
// @gmail.com - user
// @cbc.gmail.com - sales, staff
// @deliveryguy.com - delivery
export const catalogsPreviewData = [
    {
        "id": "1",
        "name": "Bathroom Toiletries",
        "image": bathroomToiletries,
    },
    {
        "id": "2",
        "name": "Cleaning Products",
        "image": cleaningProducts,
    },
    {
        "id": "3",
        "name": "Household Cleaning",
        "image": cleaningTools,
    }
];

// list of products from a specific catalog
export const catalogData: ProductData[] = [
    {
        "product_id": "001",
        "name": "Multi-purpose Cleaner Spray",
        "description": "Can be used for everyday cleaning, 500ml",
        "price": "£3.50",
        "quantity": 10,
        "catalog_id": "1",
        "image": productOne,
    },
    {
        "product_id": "002",
        "name": "Glass & Window Cleaner",
        "description": "Leaving your windows and glass surfaces shining, 450ml, pack of 3",
        "price": "£3",
        "quantity": 10,
        "catalog_id": "1",
        "image": productTwo,
    },
    {
        "product_id": "003",
        "name": "Disinfectant Wipes",
        "description": "Removes up to 99,99% bacteria from surfaces",
        "price": "£2.50",
        "quantity": 10,
        "catalog_id": "1",
        "image": productThree,
    },
    {
        "product_id": "004",
        "name": "Microfiber cleaning cloth",
        "description": "pack of 12, size 32 x 32",
        "price": "£11.99",
        "quantity": 50,
        "catalog_id": "1",
        "image": productFour,
    },
    {
        "product_id": "005",
        "name": "Dishwasher Tablets",
        "description": "Lemon scent, pack of 30",
        "price": "£5.99",
        "quantity": 10,
        "catalog_id": "2",
        "image": productFive,
    },
    {
        "product_id": "006",
        "name": "Washing Machine Cleaner",
        "description": "Removes limescale and dirt, pack of 2",
        "price": "£3.50",
        "quantity": 10,
        "catalog_id": "2",
        "image": productSix,
    },
    {
        "product_id": "007",
        "name": "Fabric Softener",
        "description": "Spring fresh scent, 1L",
        "price": "£2.50",
        "quantity": 10,
        "catalog_id": "2",
        "image": productSeven,
    },
    {
        "product_id": "008",
        "name": "Laundry Detergent",
        "description": "Lavender scent, 2L",
        "price": "£4.99",
        "quantity": 50,
        "catalog_id": "2",
        "image": productEight,
    },
    {
        "product_id": "009",
        "name": "Broom",
        "description": "Soft bristles, 1.2m",
        "price": "£5.99",
        "quantity": 10,
        "catalog_id": "3",
        "image": productNine,
    },
    {
        "product_id": "010",
        "name": "Mop",
        "description": "Microfiber mop head, 1.2m",
        "price": "£8.50",
        "quantity": 10,
        "catalog_id": "3",
        "image": productTen,
    },
    {
        "product_id": "011",
        "name": "Dustpan and Brush",
        "description": "Soft bristles, 1m",
        "price": "£3.50",
        "quantity": 10,
        "catalog_id": "3",
        "image": productEleven,
    },
]

