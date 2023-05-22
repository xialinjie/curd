// src/types/global.d.ts
declare namespace globalThis {
    var jsdom: any;
}

// src/types/global.d.ts
declare module "*.less" {
    const content: any;
    export default content;
  }