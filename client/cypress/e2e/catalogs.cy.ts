// to run the testing environment:
// run the server on the backend: mvn spring-boot:run
// run the client: npm run start
// run the test: npx cypress open

describe('project test', () => {
  // test checks if there are three catalogs on the home page
  it('catalogs preview shows three calalogs', () => {
    cy.visit('/')
    cy.get('[data-test=catalog-preview]').should('have.length', 3)
  })
  // the test checks if click on the first catalog leads to catalog page
  it('click on first catalog works correctly', () => {
    cy.visit('/')
    cy.get('[data-test=catalog-preview-item-1]').click();
      cy.location('pathname').should('eq', '/1');
  })
  // the test checks if click on the product with certain id (001) leads to the product page
  it('click on the product works correctly', () => {
    cy.visit('/1')
    cy.get('[data-test=product-preview-001]').click();
    cy.location('pathname').should('eq', '/1/001');
  })
  // the test checks if the chosen amount of the product is added to the cart
  it('add product to cart works correctly', () => {
    cy.visit('/1/001')
    cy.get('[data-test=product-quantity-input]').type('0');
    cy.get('[data-test=add-to-cart-btn]').click();
    cy.get('[data-test=product-added-message]').should('exist');
  })
})