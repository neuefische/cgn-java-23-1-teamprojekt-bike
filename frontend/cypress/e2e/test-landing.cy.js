describe('Mega Bike Master 9000 -  Landing page', () => {
   it("visits the Mega Bike Master 9000's Landing page", () => {
      cy.visit('localhost:3000')
   })
   it('should have a logo', () => {
      cy.get('.header__logo').should('be.visible')
   })
})
