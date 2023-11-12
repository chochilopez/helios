/* eslint-env node */

/*
 * This file runs in a Node context (it's NOT transpiled by Babel), so use only
 * the ES6 features that are supported by your Node version. https://node.green/
 */

// Configuration for your app
// https://v2.quasar.dev/quasar-cli-vite/quasar-config-js

const { configure } = require('quasar/wrappers')

module.exports = configure(function (ctx) {
  return {
    eslint: {
      // fix: true,
      // include: [],
      // exclude: [],
      // rawOptions: {},
      warnings: true,
      errors: true
    },

    // https://v2.quasar.dev/quasar-cli/prefetch-feature
    // preFetch: true,

    // app boot file (/src/boot)
    // --> boot files are part of "main.js"
    // https://v2.quasar.dev/quasar-cli/boot-files
    boot: [

      'axios'
    ],

    // https://v2.quasar.dev/quasar-cli-vite/quasar-config-js#css
    css: [
      'app.scss'
    ],

    // https://github.com/quasarframework/quasar/tree/dev/extras
    extras: [
      // 'ionicons-v4',
      // 'mdi-v5',
      'fontawesome-v6',
      // 'eva-icons',
      // 'themify',
      // 'line-awesome',
      'mdi-v6',
      'material-icons'
    ],

    // Full list of options: https://v2.quasar.dev/quasar-cli-vite/quasar-config-js#build
    build: {
      target: {
        browser: ['es2019', 'edge88', 'firefox78', 'chrome87', 'safari13.1'],
        node: 'node16'
      },

      vueRouterMode: 'history', // available values: 'hash', 'history'
      env: {
        API_URL: ctx.dev ? 'http://localhost:9088/api/' : 'https://helios.com.ar:9088/api/',
        APP_URL: ctx.dev ? 'http://localhost:8080' : 'https://helios.com.ar',
        APP_MULTIMEDIA_URL: ctx.dev ? 'http://localhost:8080/media/' : 'https://helios.com.ar/media/',
        APP_USERNAME_ADMIN: ctx.dev ? 'admin@helios.com' : '',
        APP_PASSWORD_ADMIN: ctx.dev ? 'contraseña' : '',
        APP_USERNAME_USUARIO: ctx.dev ? 'usuario@helios.com' : '',
        APP_PASSWORD_USUARIO: ctx.dev ? 'contraseña' : '',
        APP_USERNAME_CARGA: ctx.dev ? 'carga@helios.com' : '',
        APP_PASSWORD_CARGA: ctx.dev ? 'contraseña' : '',
        JSREPORT_URL: ctx.dev ? 'https://localhost:9094' : 'https://helios.com.ar:9094'
      }
    },

    // Full list of options: https://v2.quasar.dev/quasar-cli-vite/quasar-config-js#devServer
    devServer: {
      server: {
        type: 'http'
      },
      port: 8080,
      open: true // opens browser window automatically
    },

    // https://v2.quasar.dev/quasar-cli-vite/quasar-config-js#framework
    framework: {
      plugins: [
        'Loading',
        'Notify'
      ],
      config: {
        notify: { /* look at QuasarConfOptions from the API card */ },
        loading: { /* look at QuasarConfOptions from the API card */ }
      }
    },

    // animations: 'all', // --- includes all animations
    // https://v2.quasar.dev/options/animations
    animations: [],

    // https://v2.quasar.dev/quasar-cli-vite/quasar-config-js#property-sourcefiles
    // sourceFiles: {
    //   rootComponent: 'src/App.vue',
    //   router: 'src/router/index',
    //   store: 'src/store/index',
    //   registerServiceWorker: 'src-pwa/register-service-worker',
    //   serviceWorker: 'src-pwa/custom-service-worker',
    //   pwaManifestFile: 'src-pwa/manifest.json',
    //   electronMain: 'src-electron/electron-main',
    //   electronPreload: 'src-electron/electron-preload'
    // },

    // https://v2.quasar.dev/quasar-cli/developing-ssr/configuring-ssr
    ssr: {
      // ssrPwaHtmlFilename: 'offline.html', // do NOT use index.html as name!
      // will mess up SSR

      // extendSSRWebserverConf (esbuildConf) {},
      // extendPackageJson (json) {},

      pwa: false,

      // manualStoreHydration: true,
      // manualPostHydrationTrigger: true,

      prodPort: 3000, // The default port that the production server should use
      // (gets superseded if process.env.PORT is specified at runtime)

      middlewares: [
        'render' // keep this as last one
      ]
    },

    // https://v2.quasar.dev/quasar-cli/developing-pwa/configuring-pwa
    pwa: {
      workboxMode: 'generateSW', // or 'injectManifest'
      injectPwaMetaTags: true,
      swFilename: 'sw.js',
      manifestFilename: 'manifest.json',
      useCredentialsForManifestTag: false
      // useFilenameHashes: true,
      // extendGenerateSWOptions (cfg) {}
      // extendInjectManifestOptions (cfg) {},
      // extendManifestJson (json) {}
      // extendPWACustomSWConf (esbuildConf) {}
    },
    manifest: {
      name: 'HELIOS HIPERIONIDA',
      short_name: 'Helios',
      description: 'HELIOS | Apliacion web para empresas de transporte',
      display: 'standalone',
      orientation: 'portrait',
      background_color: '#ffffff',
      theme_color: '#6b8aad'
    //   icons: [
    //     {
    //       src: 'icons/icon-16x16.png',
    //       sizes: '16x16',
    //       type: 'image/png'
    //     },
    //     {
    //       src: 'icons/icon-32x32.png',
    //       sizes: '32x32',
    //       type: 'image/png'
    //     },
    //     {
    //       src: 'icons/icon-96x96.png',
    //       sizes: '96x96',
    //       type: 'image/png'
    //     },
    //     {
    //       src: 'icons/icon-128x128.png',
    //       sizes: '128x128',
    //       type: 'image/png'
    //     },
    //     {
    //       src: 'icons/icon-192x192.png',
    //       sizes: '192x192',
    //       type: 'image/png'
    //     },
    //     {
    //       src: 'icons/icon-256x256.png',
    //       sizes: '256x256',
    //       type: 'image/png'
    //     },
    //     {
    //       src: 'icons/icon-384x384.png',
    //       sizes: '384x384',
    //       type: 'image/png'
    //     },
    //     {
    //       src: 'icons/icon-512x512.png',
    //       sizes: '512x512',
    //       type: 'image/png'
    //     }
    //   ]
    // }
    },

    // Full list of options: https://v2.quasar.dev/quasar-cli/developing-cordova-apps/configuring-cordova
    cordova: {
      // noIosLegacyBuildFlag: true, // uncomment only if you know what you are doing
    },

    // Full list of options: https://v2.quasar.dev/quasar-cli/developing-capacitor-apps/configuring-capacitor
    capacitor: {
      hideSplashscreen: true
    },

    // Full list of options: https://v2.quasar.dev/quasar-cli/developing-electron-apps/configuring-electron
    electron: {
      // extendElectronMainConf (esbuildConf)
      // extendElectronPreloadConf (esbuildConf)

      // specify the debugging port to use for the Electron app when running in development mode
      inspectPort: 5858,

      bundler: 'packager', // 'packager' or 'builder'

      packager: {
        // https://github.com/electron-userland/electron-packager/blob/master/docs/api.md#options

        // OS X / Mac App Store
        // appBundleId: '',
        // appCategoryType: '',
        // osxSign: '',
        // protocol: 'myapp://path',

        // Windows only
        // win32metadata: { ... }
      },

      builder: {
        // https://www.electron.build/configuration/configuration

        appId: 'quasar-project'
      }
    },

    // Full list of options: https://v2.quasar.dev/quasar-cli-vite/developing-browser-extensions/configuring-bex
    bex: {
      contentScripts: [
        'my-content-script'
      ]

      // extendBexScriptsConf (esbuildConf) {}
      // extendBexManifestJson (json) {}
    }
  }
})
