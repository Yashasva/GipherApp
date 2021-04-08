import { browser, by, element, ElementFinder, promise } from 'protractor';

export class AppPage {
  async navigateTo(): Promise<unknown> {
    return browser.get(browser.baseUrl);
  }

  async getTitleText(): Promise<string> {
    return element(by.css('app-root .content span')).getText();
  }

  // get header
  getNavbarElement(): ElementFinder {
    return element(by.className('navbar'));
  }
  getListElement(): ElementFinder {
    return element(by.name('HOME'));
  }
  getFormElement(): ElementFinder {
    return element(by.className('form-inline'));
  }
  getRegisterBut(): ElementFinder {
    return element(by.className('btn-outline-light'));
  }
  // check header is present or not
  isNavBarPresent(): promise.Promise<boolean> {
    return this.getNavbarElement().isPresent();
  }
  isListPresent(): promise.Promise<boolean> {
    return this.getListElement().isPresent();
  } 
  isFormPresent(): promise.Promise<boolean> {
    return this.getFormElement().isPresent();
  } 
  // isLoginPresent(): promise.Promise<boolean> {
  //   return this.getLoginBut().isPresent();
  // }
  isRegisterPresent(): promise.Promise<boolean> {
    return this.getRegisterBut().isPresent();
  }
}
