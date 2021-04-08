import { browser, logging, element,by} from 'protractor';
 import { AppPage } from './app.po';
describe('Gipher e2e tests', () => {
  it('should display home', () => {
    browser.get('/');
    expect(browser.getCurrentUrl()).toContain('home');
    browser.sleep(5000);})
  })
  describe('Gipher e2e tests', () => {
    it('should display favourite', () => {
      browser.get('/favorites');
      expect(browser.getCurrentUrl()).toContain('favorites');
      browser.sleep(5000);})
    })
    describe('Gipher e2e tests', () => {
      it('should display feedabck', () => {
        browser.get('/feedback');
        expect(browser.getCurrentUrl()).toContain('feedback');
        browser.sleep(5000);})
      })
      describe('Gipher e2e tests', () => {
        it('should display search', () => {
          browser.get('/search');
          expect(browser.getCurrentUrl()).toContain('search');
          browser.sleep(5000);})
        })
        describe('Gipher e2e tests', () => {
          it('should display register', () => {
            browser.get('/register');
            expect(browser.getCurrentUrl()).toContain('register');
            browser.sleep(5000);})
          })
          describe('Gipher e2e tests', () => {
            it('should display login', () => {
              browser.get('/login');
              expect(browser.getCurrentUrl()).toContain('login');
              browser.sleep(5000);})
            })
            describe('workspace-project App', () => {
              let page: AppPage;
              beforeEach(() => {
                page = new AppPage();
              });
              it('should display Navbar Element', () => {
                page.navigateTo();
                expect(page.getNavbarElement()).toBeTruthy();
                browser.sleep(2000);
              });
              it('should display List Element', () => {
                page.navigateTo();
                expect(page.getListElement()).toBeTruthy();
                browser.sleep(2000);
              });
              it('should display Form Element', () => {
                page.navigateTo();
                expect(page.getFormElement()).toBeTruthy();
                browser.sleep(2000);
              });
 
              it('should have Register Button', () => {
                page.navigateTo();
                expect(page.getRegisterBut()).toBeTruthy();
                browser.sleep(2000);
              });
});