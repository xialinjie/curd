// tests/jest-setup.ts
// 使用 Jest 的 Spy 和扩展 expect 来 Mock `window.location`
// import "jest-location-mock";
import '@testing-library/jest-dom'

jest.spyOn(console, 'log').mockReturnValue();
jest.spyOn(console, 'info').mockReturnValue();
jest.spyOn(console, 'warn').mockReturnValue();
jest.spyOn(console, 'error').mockReturnValue();

// 详情：https://jestjs.io/docs/manual-mocks#mocking-methods-which-are-not-implemented-in-jsdom
Object.defineProperty(window, 'matchMedia', {
  writable: true,
  value: jest.fn().mockImplementation(query => ({
    matches: false,
    media: query,
    onchange: null,
    addListener: jest.fn(), // deprecated
    removeListener: jest.fn(), // deprecated
    addEventListener: jest.fn(),
    removeEventListener: jest.fn(),
    dispatchEvent: jest.fn(),
  })),
});

import server from "./mockServer/server";

beforeAll(() => {
  server.listen();
});

afterEach(() => {
  server.resetHandlers();
});

afterAll(() => {
  server.close();
});


// Object.defineProperty(global, 'localStorage', {
//     value: {
//       store: {} as Record<string, string>,
//       setItem(key: string, value: string) {
//         this.store[key] = value;
//       },
//       getItem(key: string) {
//         return this.store[key];
//       },
//       removeItem(key: string) {
//         delete this.store[key];
//       },
//       clear() {
//         this.store = {}
//       }
//     },
//     configurable: true,
//   })