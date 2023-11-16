<template>
  <q-card class="font-5 no-shadow no-border"> </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table title="Facturas"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        hide-no-data
        :rows="facturas"
        row-key="id"
      >
        <template v-slot:top-left>
          <div class="column">
            <p class="text-h5">Facturas</p>
            <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo factura" @click="fMostrarNuevoFactura" />
          </div>
        </template>
        <template v-slot:top-right>
          <div class="column items-end">
            <div class="q-my-md">
              <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar facturas por" dropdown-icon="fa-solid fa-magnifying-glass">
                <q-list>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarComprador">
                    <q-item-section avatar>
                      <q-icon name="monetization_on" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Comprador</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarFechaFacturacion">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-calendar-days" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Fecha facturación</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarFechaViaje">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-truck-arrow-right" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Fecha viaje</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNumeroComprobante">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-file-invoice" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Número comprobante</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNumeroGuia">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-shuffle" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Numero guía</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNumeroRemito">
                    <q-item-section avatar>
                      <q-icon name="pin" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Número remito</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarTipoComprobante">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-file-lines" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Tipo comprobante</q-item-label>
                    </q-item-section>
                  </q-item>
                </q-list>
              </q-btn-dropdown>
            </div>
            <div class="col-md-4">
              <q-select
                v-if="editComprador"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="comprador"
                :options="compradores"
                option-value="id"
                option-label="nombre"
                label="Buscar por comprador"
                use-input
                input-debounce="0"
                @filter="fFiltrarCompradores"
                @update:model-value="afBuscarPorCompradorId()"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
                <template v-slot:before>
                  <q-icon name="monetization_on" class="q-mx-xs" />
                </template>
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
              <div class="column" v-if="editFechaFacturacion">
                <div class="row justify-around">
                  <q-input
                    mask="##-##-####"
                    style="width: 180px"
                    v-model="fechaFacturacion.from"
                    outlined
                    dense
                    clearable
                    label="Facturacion fin"
                    hint="20-01-2020"
                  >
                    <template v-slot:before>
                      <q-icon name="event" class="cursor-pointer">
                        <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                          <q-date v-model="fechaFacturacion.from" mask="DD-MM-YYYY">
                            <div class="row items-center justify-end">
                              <q-btn v-close-popup label="OK" color="primary" flat />
                            </div>
                          </q-date>
                        </q-popup-proxy>
                      </q-icon>
                    </template>
                  </q-input>
                  <q-input
                    class="q-ml-md"
                    mask="##-##-####"
                    style="width: 180px"
                    v-model="fechaFacturacion.to"
                    outlined
                    dense
                    clearable
                    label="Facturacion inicio"
                    hint="30-01-2020"
                  >
                    <template v-slot:before>
                      <q-icon name="event" class="cursor-pointer">
                        <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                          <q-date v-model="fechaFacturacion.to" mask="DD-MM-YYYY">
                            <div class="row items-center justify-end">
                              <q-btn v-close-popup label="OK" color="primary" flat />
                            </div>
                          </q-date>
                        </q-popup-proxy>
                      </q-icon>
                    </template>
                  </q-input>
                  <div class="col">
                    <q-icon name="fa-solid fa-magnifying-glass" size="24px" class="cursor-pointer q-pa-sm edits" v-on:click="afBuscarPorFechaViaje()" />
                  </div>
                </div>
              </div>
              <q-input
                v-if="editNumeroGuia"
                outlined
                dense
                clearable
                v-on:keyup.enter="afBuscarPorTipoComprobante()"
                v-model="numeroGuia"
                label="Buscar por número guía"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
                <template v-slot:before>
                  <q-icon name="fa-solid fa-shuffle" class="q-mx-xs" />
                </template>
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
                <template v-slot:after>
                  <q-icon name="fa-solid fa-magnifying-glass" class="q-mx-xs" v-on:click="afBuscarPorTipoComprobante()" style="cursor: pointer" />
                </template>
              </q-input>
            </div>
          </div>
        </template>
        <template v-slot:body="props">
          <q-tr :props="props" :class="{ 'bg-teal-2': props.row.pagada, 'bg-red-2': props.row.eliminada } ">
            <q-td auto-width class="text-center">
              <q-btn
                size="sm"
                class="text-white q-mr-xs"
                :class="props.expand ? 'paleta5-fondo3' : 'paleta5-fondo2'"
                round
                dense
                @click="props.expand = !props.expand"
              >
                <q-icon size="2em" class="q-pa-xs" :name="props.expand ? 'zoom_out' : 'zoom_in'" />
                <q-tooltip>
                  Expandir
                </q-tooltip>
              </q-btn>
              <q-btn
                v-if="props.row.eliminada === null"
                size="sm"
                class="text-white paleta5-fondo2 q-mr-xs"
                round
                dense
                @click="fMostrarEditarFactura(props)"
              >
                <q-icon size="2em" class="q-pa-xs" name="edit" />
                <q-tooltip>
                  Modificar
                </q-tooltip>
              </q-btn>
              <q-btn
                v-if="props.row.eliminada === null"
                size="sm"
                class="text-white paleta5-fondo2 q-mr-xs"
                round
                dense
                @click="fMostrarEliminarFactura(props)"
              >
                <q-icon size="2em" class="q-pa-xs" name="delete" />
                <q-tooltip>
                  Eliminar
                </q-tooltip>
              </q-btn>
              <q-btn
                v-if="props.row.eliminada !== null"
                size="sm"
                class="text-white paleta5-fondo2 q-mr-xs"
                round
                dense
                @click="fMostrarReciclarFactura(props)"
              >
                <q-icon size="2em" class="q-pa-xs" name="recycling" />
                <q-tooltip>
                  Reciclar
                </q-tooltip>
              </q-btn>
            </q-td>
            <q-td class="text-center">
              {{ props.row.tipoComprobante }}
            </q-td>
            <q-td class="text-center">
              {{ fFormatoFecha(props.row.numeroComprobante) }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.fechaFacturacion }}
            </q-td>
            <q-td>
              {{ props.row.destino }}
            </q-td>
            <q-td>
              {{ props.row.conductor }}
            </q-td>
            <q-td>
              {{ props.row.camion }}
            </q-td>
            <q-td>
              {{ props.row.categoriaViaje }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.cantidadTransportada }}
            </q-td>
            <q-td class="text-center">
              {{ fMostrarTotal(props.row) }}
            </q-td>
          </q-tr>
          <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
            <q-td colspan="100%">
              <div class="row">
                <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.id }}</div>
                  <div class="row paleta1-color2">Id</div>
                </div>
                <div v-if="props.row.camion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.camion }}</div>
                  <div class="row paleta1-color2">camion</div>
                </div>
                <div v-if="props.row.cantidadTransportada != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.cantidadTransportada }}</div>
                  <div class="row paleta1-color2">cantidadTransportada</div>
                </div>
                <div v-if="props.row.categoriaViaje != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.categoriaViaje }}</div>
                  <div class="row paleta1-color2">Categoria viaje</div>
                </div>
                <div v-if="props.row.comprador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.comprador }}</div>
                  <div class="row paleta1-color2">Comprador</div>
                </div>
                <div v-if="props.row.conductor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.conductor }}</div>
                  <div class="row paleta1-color2">conductor</div>
                </div>
                <div v-if="props.row.descuento != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.descuento }}</div>
                  <div class="row paleta1-color2">descuento</div>
                </div>
                <div v-if="props.row.destino != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.destino }}</div>
                  <div class="row paleta1-color2">destino</div>
                </div>
                <div v-if="props.row.fecha != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.fecha }}</div>
                  <div class="row paleta1-color2">Fecha del factura</div>
                </div>
                <div v-if="props.row.fechaViaje != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.fechaViaje }}</div>
                  <div class="row paleta1-color2">fechaViaje</div>
                </div>
                <div v-if="props.row.iva != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.iva }}</div>
                  <div class="row paleta1-color2">iva</div>
                </div>
                <div v-if="props.row.kmCargado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.kmCargado }}</div>
                  <div class="row paleta1-color2">kmCargado</div>
                </div>
                <div v-if="props.row.numeroComprobante != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.numeroComprobante }}</div>
                  <div class="row paleta1-color2">numeroComprobante</div>
                </div>
                <div v-if="props.row.numeroGuia != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.numeroGuia }}</div>
                  <div class="row paleta1-color2">numeroGuia</div>
                </div>
                <div v-if="props.row.origen != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.origen }}</div>
                  <div class="row paleta1-color2">origen</div>
                </div>
                <div v-if="props.row.pagada != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.pagada }}</div>
                  <div class="row paleta1-color2">pagada</div>
                </div>
                <div v-if="props.row.recarga != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                  <div class="row text-white">{{ props.row.recarga }}</div>
                  <div class="row paleta1-color2">recarga</div>
                </div>
                <div v-if="props.row.remito != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.remito }}</div>
                  <div class="row paleta1-color2">remito</div>
                </div>
                <div v-if="props.row.subTotal != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.subTotal }}</div>
                  <div class="row paleta1-color2">subTotal</div>
                </div>
                <div v-if="props.row.tipoComprobante != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                  <div class="row text-white">{{ props.row.tipoComprobante }}</div>
                  <div class="row paleta1-color2">tipoComprobante</div>
                </div>
                <div v-if="props.row.valorKm != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.valorKm }}</div>
                  <div class="row paleta1-color2">valorKm</div>
                </div>
                <div v-if="props.row.creador != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.creador }}</div>
                  <div class="row paleta1-color2">Creador</div>
                </div>
                <div v-if="props.row.creada != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.creada }}</div>
                  <div class="row paleta1-color2">Creado</div>
                </div>
                <div v-if="props.row.modificador != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.modificador }}</div>
                  <div class="row paleta1-color2">Modificador</div>
                </div>
                <div v-if="props.row.modificada != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.modificada }}</div>
                  <div class="row paleta1-color2">Modificado</div>
                </div>
                <div v-if="props.row.eliminador != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.eliminador }}</div>
                  <div class="row paleta1-color2">Eliminador</div>
                </div>
                <div v-if="props.row.eliminada != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.eliminada }}</div>
                  <div class="row paleta1-color2">Eliminada</div>
                </div>
                <div v-if="props.row.notas != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.notas }}</div>
                  <div class="row paleta1-color2">Notas</div>
                </div>
              </div>
            </q-td>
          </q-tr>
        </template>
      </q-table>
    </div>
  </div>

  <q-dialog v-model="nuevaFacturaDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">{{ titulo }}</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
        <q-icon name="img:/icons/numeros/number2.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso2 }" />
        <q-icon name="img:/icons/numeros/number3.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso3 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fIrPaso2">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##-##-####"
                v-model="viajeCreation.fecha"
                :rules="[reglas.requerido]"
                outlined
                dense
                readonly
                clearable
                label="Fecha del factura"
                hint="Formato dd-mm-yyyy."
              >
                <template v-slot:prepend>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="viajeCreation.fecha" mask="DD-MM-YYYY" :locale="myLocale">
                        <div class="row items-center justify-end">
                          <q-btn v-close-popup label="OK" color="primary" flat />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="viajeCreation.compradorId"
                :rules="[reglas.requerido]"
                :options="compradores"
                option-value="id"
                option-label="nombre"
                label="Comprador"
                use-input
                input-debounce="0"
                @filter="fFiltrarCompradores"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="viajeCreation.categoriaViajeId"
                :rules="[reglas.requerido]"
                option-value="id"
                option-label="categoria"
                label="Categoria de factura"
                use-input
                hide-selected
                fill-input
                :options="categoriasViaje"
                @filter="fFiltrarCategoriasViaje"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="viajeCreation.cantidadTransportada"
                :rules="[reglas.requerido]"
                mask="###################"
                outlined
                dense
                clearable
                label="Cantidad transportada"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="viajeCreation.vendedorId"
                :options="vendedores"
                option-value="id"
                option-label="nombre"
                label="Vendedor"
                use-input
                input-debounce="0"
                @filter="fFiltrarVendedores"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="viajeCreation.intermediarioId"
                :options="intermediarios"
                option-value="id"
                option-label="nombre"
                label="Intermediario"
                use-input
                input-debounce="0"
                @filter="fFiltrarIntermediarios"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-end q-pa-md">
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="arrow_right_alt" ripple>
              Siguiente
            </q-btn>
          </div>
        </q-form>
      </q-card-section>

      <q-card-section v-if="paso2">
        <q-form v-on:submit.prevent="fIrPaso3">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="viajeCreation.conductorId"
                :options="conductores"
                option-value="id"
                option-label="nombre"
                label="Buscar por conductor"
                use-input
                input-debounce="0"
                @filter="fFiltrarConductores"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="viajeCreation.camionId"
                :rules="[reglas.requerido]"
                :options="camiones"
                option-value="id"
                option-label="marcaModelo"
                label="Camión"
                use-input
                input-debounce="0"
                @filter="fFiltrarCamiones"
                hint="Ingresá 3 caracteres para buscar."
                hide-selected
                fill-input
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                hide-selected
                fill-input
                v-model="viajeCreation.acopladoId"
                :rules="[reglas.requerido]"
                :options="acoplados"
                option-value="id"
                option-label="marcaModelo"
                label="Acoplado"
                use-input
                input-debounce="0"
                @filter="fFiltrarAcoplados"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="viajeCreation.cargaId"
                :options="direccionesCarga"
                option-value="id"
                option-label="direccion"
                label="Direccion de carga"
                use-input
                input-debounce="0"
                @filter="fFiltrarDireccionesCarga"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="viajeCreation.origenId"
                :rules="[reglas.requerido]"
                :options="direccionesOrigen"
                option-value="id"
                option-label="direccion"
                label="Direccion de origen"
                use-input
                input-debounce="0"
                @filter="fFiltrarDireccionesOrigen"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="viajeCreation.destinoId"
                :rules="[reglas.requerido]"
                :options="direccionesDestino"
                option-value="id"
                option-label="direccion"
                label="Direccion de destino"
                use-input
                input-debounce="0"
                @filter="fFiltrarDireccionesDestino"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-end q-pa-md">
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="arrow_right_alt" ripple>
              Siguiente
            </q-btn>
          </div>
        </q-form>
      </q-card-section>

      <q-card-section v-if="paso3">
        <q-form v-on:submit.prevent="fGuardarViaje">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##############"
                v-model.number="viajeCreation.neto"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Peso neto aprox"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="viajeCreation.guia"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Número de guía"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##############"
                v-model.number="viajeCreation.kmVacio"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Kilometros vacio"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##############"
                v-model.number="viajeCreation.kmCargado"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Kilometros cargado"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model.number="viajeCreation.valorKm"
                :rules="[reglas.requerido]"
                :max-decimals="2"
                type="number"
                outlined
                dense
                clearable
                label="Valor kilometro"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="viajeCreation.notas"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              />
            </div>
          </div>
          <div class="row justify-end q-pa-md">
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="save" ripple>
              Finalizar
            </q-btn>
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { acopladoService } from 'src/services/acoplado_service'
import { autenticacionService } from 'src/services/autenticacion_service'
import { ayuda } from 'app/src/helpers/ayuda'
import { camionService } from 'src/services/camion_service'
import { categoriaViajeService } from 'src/services/categoria_viaje_service'
import { conductorService } from 'src/services/conductor_service'
import { clienteService } from 'src/services/cliente_service'
import { direccionService } from 'src/services/direccion_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { onMounted, reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { rolEnum } from 'src/models/enums/rol_enum'
import { useQuasar } from 'quasar'
import { useRouter } from 'vue-router'
import { v4 as uuidv4 } from 'uuid'
import { ViajeCreation } from 'src/models/creation/viaje_creation'
import { viajeService } from 'src/services/viaje_service'

const paginacion = {
  rowsPerPage: 50,
  sortBy: 'id',
  descending: true
}

const columnas = [
  {
    label: 'Acciones',
    align: 'center'
  },
  {
    name: 'tipoComprobante',
    label: 'Tipo comprobante',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'numeroComprobante',
    label: 'Número comprobante',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'fechaFacturacion',
    label: 'Fecha facturación',
    align: 'center',
    field: '',
    sortable: true
  },
  {
    name: 'destino',
    label: 'Destino',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'conductor',
    label: 'Conductor',
    align: 'left',
    field: ''
  },
  {
    name: 'camion',
    label: 'Camión',
    align: 'left',
    field: ''
  },
  {
    name: 'categoriaViaje',
    label: 'Categoría factura',
    align: 'left',
    field: ''
  },
  {
    name: 'cantidadTransportada',
    label: 'Cantidad',
    align: 'center',
    field: ''
  },
  {
    name: 'total',
    label: 'Total',
    align: 'center',
    field: ''
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const sesion = ref(uuidv4())
    const reglas = reactive(reglasValidacion.reglas)
    const esAdmin = ref(
      autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)
    )
    const router = useRouter()
    const titulo = ref(null)

    const editAcoplado = ref(false)
    const editCamion = ref(false)
    const editCategoriaViaje = ref(false)
    const editComprador = ref(true)
    const editCantidadTransportada = ref(false)
    const editConductor = ref(false)
    const editDireccionCarga = ref(false)
    const editDireccionDestino = ref(false)
    const editDireccionOrigen = ref(false)
    const editFecha = ref(false)
    const editIntermediario = ref(false)
    const editKilometrosCargado = ref(false)
    const editKilometrosVacio = ref(false)
    const editNotas = ref(false)
    const editNumeroGuia = ref(false)
    const editPesoNeto = ref(false)
    const editValorKilomertro = ref(false)
    const editVendedor = ref(false)
    const nuevaFacturaDialog = ref(false)

    const acoplado = ref(null)
    const acoplados = ref(null)
    const acopladosList = ref([])
    const camion = ref(null)
    const camiones = ref([])
    const camionesList = ref([])
    const cantidadTransportada = ref({ min: 0, max: 300 })
    const cantidadTransportadaChip = ref({ izq: false, der: false })
    const categoriaViaje = ref(null)
    const categoriasViaje = ref([])
    const categoriasViajeList = ref([])
    const comprador = ref(null)
    const compradores = ref([])
    const conductor = ref(null)
    const conductores = ref([])
    const conductoresList = ref([])
    const direccionCarga = ref(null)
    const direccionesCarga = ref([])
    const direccionDestino = ref(null)
    const direccionesDestino = ref([])
    const direccionOrigen = ref(null)
    const direccionesOrigen = ref([])
    const direccionesList = ref([])
    const fecha = ref({ from: null, to: null })
    const intermediario = ref(null)
    const intermediarios = ref([])
    const kmCargado = ref({ min: 0, max: 1000 })
    const kmCargadoChip = ref({ izq: false, der: false })
    const kmVacio = ref({ min: 0, max: 1000 })
    const kmVacioChip = ref({ izq: false, der: false })
    const notas = ref(null)
    const numeroGuia = ref(null)
    const numerosGuia = ref([])
    const pesoNeto = ref({ min: 0, max: 50000 })
    const pesoNetoChip = ref({ izq: false, der: false })
    const valorKm = ref({ min: 0, max: 2000 })
    const valorKmChip = ref({ izq: false, der: false })
    const vendedor = ref(null)
    const vendedores = ref([])

    const clientesList = ref([])
    const nuevaBusqueda = ref(false)
    const paso1 = ref(true)
    const paso2 = ref(false)
    const paso3 = ref(false)
    const viajeCreation = reactive(new ViajeCreation())
    const facturas = ref([])

    afBuscarPaginadas()

    onMounted(() => {
      if (llaveroService.obtenerDeLocal('hhConfirmarPresupuesto') !== null) {
        const resultado = llaveroService.obtenerDeLocal('hhConfirmarPresupuesto')
        viajeCreation.id = null
        viajeCreation.cantidadTransportada = resultado.value.cantidadTransportada
        viajeCreation.categoriaViajeId = resultado.value.categoriaViajeId
        viajeCreation.compradorId = resultado.value.compradorId
        viajeCreation.destinoId = resultado.value.destinoId
        viajeCreation.fecha = resultado.value.fecha
        viajeCreation.fechaId = resultado.value.fechaId
        viajeCreation.kmCargado = resultado.value.kmCargado
        viajeCreation.notas = resultado.value.notas
        viajeCreation.origenId = resultado.value.origenId
        viajeCreation.valorKm = resultado.value.editValorKilomertro
        afBuscarConductores().then(() => {
          afBuscarClientes().then(() => {
            afBuscarAcoplados().then(() => {
              afBuscarCamiones().then(() => {
                afBuscarCategoriasViaje().then(() => {
                  afBuscarDirecciones().then(() => {
                    titulo.value = 'Confirmar presupuesto de factura para ' + resultado.value.comprador
                    fIrPaso1()
                    llaveroService.borrarDeLocal('hhConfirmarPresupuesto')
                    nuevaFacturaDialog.value = true
                  })
                })
              })
            })
          })
        })
      }
    })

    async function afBuscarAcoplados () {
      $q.loading.show()
      try {
        let resultado = null
        if (esAdmin.value) {
          if (llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConEliminadasConSesion', sesion.value) !== null) {
            acopladosList.value = llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConEliminadasConSesion', sesion.value).value
            console.log('AcopladoService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await acopladoService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              acopladosList.value = resultado.data
              console.log('AcopladoService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConSesion', sesion.value) !== null) {
            acopladosList.value = llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConSesion', sesion.value).value
            console.log('AcopladoService: Sesion recargada.')
          } else {
            resultado = await acopladoService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              acopladosList.value = resultado.data
              console.log('AcopladoService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarCamiones () {
      $q.loading.show()
      try {
        let resultado = null
        if (esAdmin.value) {
          if (llaveroService.obtenerDeLocalConSesion('hhCamionTodasConEliminadasConSesion', sesion.value) !== null) {
            camionesList.value = llaveroService.obtenerDeLocalConSesion('hhCamionTodasConEliminadasConSesion', sesion.value).value
            console.log('CamionService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await camionService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              camionesList.value = resultado.data
              console.log('CamionService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhCamionTodasConSesion', sesion.value) !== null) {
            camionesList.value = llaveroService.obtenerDeLocalConSesion('hhCamionTodasConSesion', sesion.value).value
            console.log('CamionService: Sesion recargada.')
          } else {
            resultado = await camionService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              camionesList.value = resultado.data
              console.log('CamionService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarCategoriasViaje () {
      $q.loading.show()
      try {
        let resultado = null
        if (esAdmin.value) {
          if (llaveroService.obtenerDeLocalConSesion('hhCategoriaViajeTodasConEliminadasConSesion', sesion.value) !== null) {
            categoriasViajeList.value = llaveroService.obtenerDeLocalConSesion('hhCategoriaViajeTodasConEliminadasConSesion', sesion.value).value
            console.log('CategoriaViajeService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await categoriaViajeService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              categoriasViajeList.value = resultado.data
              console.log('CategoriaViajeService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhCategoriaViajeTodasConSesion', sesion.value) !== null) {
            categoriasViajeList.value = llaveroService.obtenerDeLocalConSesion('hhCategoriaViajeTodasConSesion', sesion.value).value
            console.log('CategoriaViajeService: Sesion recargada.')
          } else {
            resultado = await categoriaViajeService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              categoriasViajeList.value = resultado.data
              console.log('CategoriaViajeService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarClientes () {
      $q.loading.show()
      try {
        let resultado = null
        if (esAdmin.value) {
          if (llaveroService.obtenerDeLocalConSesion('hhClienteTodasConEliminadasConSesion', sesion.value) !== null) {
            clientesList.value = llaveroService.obtenerDeLocalConSesion('hhClienteTodasConEliminadasConSesion', sesion.value).value
            console.log('ClienteService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await clienteService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              clientesList.value = resultado.data
              console.log('ClienteService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhClienteTodasConSesion', sesion.value) !== null) {
            clientesList.value = llaveroService.obtenerDeLocalConSesion('hhClienteTodasConSesion', sesion.value).value
            console.log('ClienteService: Sesion recargada.')
          } else {
            resultado = await clienteService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              clientesList.value = resultado.data
              console.log('ClienteService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarConductores () {
      $q.loading.show()
      try {
        let resultado = null
        if (esAdmin.value) {
          if (llaveroService.obtenerDeLocalConSesion('hhConductorTodasConEliminadasConSesion', sesion.value) !== null) {
            conductoresList.value = llaveroService.obtenerDeLocalConSesion('hhConductorTodasConEliminadasConSesion', sesion.value).value
            console.log('ConductorService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await conductorService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              conductoresList.value = resultado.data
              console.log('ConductorService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhConductorTodasConSesion', sesion.value) !== null) {
            conductoresList.value = llaveroService.obtenerDeLocalConSesion('hhConductorTodasConSesion', sesion.value).value
            console.log('ConductorService: Sesion recargada.')
          } else {
            resultado = await conductorService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              conductoresList.value = resultado.data
              console.log('ConductorService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarDirecciones () {
      $q.loading.show()
      try {
        let resultado = null
        if (esAdmin.value) {
          if (llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConEliminadasConSesion', sesion.value) !== null) {
            direccionesList.value = llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConEliminadasConSesion', sesion.value).value
            console.log('DireccionService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await direccionService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              direccionesList.value = resultado.data
              console.log('DireccionService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConSesion', sesion.value) !== null) {
            direccionesList.value = llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConSesion', sesion.value).value
            console.log('DireccionService: Sesion recargada.')
          } else {
            resultado = await direccionService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              direccionesList.value = resultado.data
              console.log('DireccionService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarPaginadas () {
      $q.loading.show()
      try {
        const paginadoDTO = {
          direccion: 'DESC',
          campo: 'creada',
          pagina: '0',
          elementos: '50'
        }
        let resultado = null
        if (esAdmin.value) {
          resultado = await viajeService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await viajeService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          facturas.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          facturas.value = []
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarPorAcopladoId () {
      if (acoplado.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorAcopladoIdConEliminadas(acoplado.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorAcopladoId(acoplado.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorCamionId () {
      if (camion.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorCamionIdConEliminadas(camion.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorCamionId(camion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorCantidadTransportada () {
      if (cantidadTransportadaChip.value.izq === true && cantidadTransportadaChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorRangoCantidadTransportadaConEliminadas(cantidadTransportada.value.min, cantidadTransportada.value.max)
          } else {
            resultado = await viajeService.spfBuscarTodasPorRangoCantidadTransportada(cantidadTransportada.value.min, cantidadTransportada.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
        cantidadTransportadaChip.value.izq = false
        cantidadTransportadaChip.value.der = false
      }
    }

    async function afBuscarPorCategoriaViajeId () {
      if (categoriaViaje.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorCategoriaViajeIdConEliminadas(categoriaViaje.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorCategoriaViajeId(categoriaViaje.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorCompradorId () {
      if (comprador.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorCompradorIdConEliminadas(comprador.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorCompradorId(comprador.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorConductorId () {
      if (conductor.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorConductorIdConEliminadas(conductor.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorConductorId(conductor.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorDireccionCargaId () {
      if (direccionCarga.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorDireccionCargaIdConEliminadas(direccionCarga.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorDireccionCargaId(direccionCarga.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorDireccionDestinoId () {
      if (direccionDestino.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorDireccionDestinoIdConEliminadas(direccionDestino.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorDireccionDestinoId(direccionDestino.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorDireccionOrigenId () {
      if (direccionOrigen.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorDireccionOrigenIdConEliminadas(direccionOrigen.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorDireccionOrigenId(direccionOrigen.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorIntermediarioId () {
      if (intermediario.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorIntermediarioIdConEliminadas(intermediario.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorIntermediarioId(intermediario.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorFechaViaje () {
      if (fecha.value.from == null) {
        notificarService.notificarAlerta('El campo fecha desde no puede estar vacio.')
      } else if (fecha.value.to == null) {
        notificarService.notificarAlerta('El campo fecha hasta no puede estar vacio.')
      } else if (fecha.value.from > fecha.value.to) {
        notificarService.notificarAlerta('El campo fecha desde no puede ser mayor que fecha hasta.')
      } else {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorFechaViajeEntreFechasConEliminadas(ayuda.fFormatearDeDatePicker(fecha.value.from), ayuda.fFormatearDeDatePicker(fecha.value.to))
          } else {
            resultado = await viajeService.spfBuscarTodasPorFechaViajeEntreFechas(ayuda.fFormatearDeDatePicker(fecha.value.from), ayuda.fFormatearDeDatePicker(fecha.value.to))
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorKilometrosCargado () {
      if (kmCargadoChip.value.izq === true && kmCargadoChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorRangoKmCargadoConEliminadas(kmCargado.value.min, kmCargado.value.max)
          } else {
            resultado = await viajeService.spfBuscarTodasPorRangoKmCargado(kmCargado.value.min, kmCargado.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
        kmCargadoChip.value.izq = false
        kmCargadoChip.value.der = false
      }
    }

    async function afBuscarPorKilometrosVacio () {
      if (kmVacioChip.value.izq === true && kmVacioChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorRangoKmVacioConEliminadas(kmVacio.value.min, kmVacio.value.max)
          } else {
            resultado = await viajeService.spfBuscarTodasPorRangoKmVacio(kmVacio.value.min, kmVacio.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
        kmVacioChip.value.izq = false
        kmVacioChip.value.der = false
      }
    }

    async function afBuscarPorNotas () {
      if (notas.value !== null && notas.value.length > 2) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorGuia () {
      if (numeroGuia.value !== null && numeroGuia.value.length > 2) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorGuiaConEliminadas(numeroGuia.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorGuia(numeroGuia.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorPesoNeto () {
      if (pesoNetoChip.value.izq === true && pesoNetoChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorRangoNetoConEliminadas(pesoNeto.value.min, pesoNeto.value.max)
          } else {
            resultado = await viajeService.spfBuscarTodasPorRangoNeto(pesoNeto.value.min, pesoNeto.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
        pesoNetoChip.value.izq = false
        pesoNetoChip.value.der = false
      }
    }

    async function afBuscarPorValorKm () {
      if (valorKmChip.value.izq === true && valorKmChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorRangoValorKmConEliminadas(valorKm.value.min, valorKm.value.max)
          } else {
            resultado = await viajeService.spfBuscarTodasPorRangoValorKm(valorKm.value.min, valorKm.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
        valorKmChip.value.izq = false
        valorKmChip.value.der = false
      }
    }

    async function afBuscarPorVendedorId () {
      if (vendedor.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await viajeService.spfBuscarTodasPorVendedorIdConEliminadas(vendedor.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorVendedorId(vendedor.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afGuardarViaje () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await viajeService.spfGuardar(viajeCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el factura.')
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afEliminarViaje (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await viajeService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el factura.')
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afReciclarViaje (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await viajeService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el factura.')
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    function fFiltrarAcoplados (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        acoplados.value = acopladosList.value.filter(
          (v) => v.marcaModelo.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarCamiones (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        camiones.value = camionesList.value.filter(
          (v) => v.marcaModelo.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarCategoriasViaje (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        categoriasViaje.value = categoriasViajeList.value.filter(
          (v) => v.categoria.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarCompradores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        compradores.value = clientesList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarConductores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        conductores.value = conductoresList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarDireccionesCarga (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        direccionesCarga.value = direccionesList.value.filter(
          (v) => { return v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1 || v.ciudad.toLowerCase().indexOf(val.toLowerCase()) > -1 }
        )
      })
    }

    function fFiltrarDireccionesDestino (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        direccionesDestino.value = direccionesList.value.filter(
          (v) => { return v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1 || v.ciudad.toLowerCase().indexOf(val.toLowerCase()) > -1 }
        )
      })
    }

    function fFiltrarDireccionesOrigen (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        direccionesOrigen.value = direccionesList.value.filter(
          (v) => { return v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1 || v.ciudad.toLowerCase().indexOf(val.toLowerCase()) > -1 }
        )
      })
    }

    function fFiltrarIntermediarios (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        intermediarios.value = clientesList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarVendedores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        vendedores.value = clientesList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fGuardarViaje () {
      afGuardarViaje().then(() => {
        afBuscarPaginadas().then(() => {
          nuevaFacturaDialog.value = false
          fIrPaso1()
        })
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      paso2.value = false
      paso3.value = false
    }

    function fIrPaso2 () {
      paso1.value = false
      paso2.value = true
    }

    function fIrPaso3 () {
      paso2.value = false
      paso3.value = true
    }

    function fLimpiarFormulario () {
      viajeCreation.acopladoId = null
      viajeCreation.camionId = null
      viajeCreation.cantidadTransportada = null
      viajeCreation.cargaId = null
      viajeCreation.categoriaViajeId = null
      viajeCreation.compradorId = null
      viajeCreation.conductorId = null
      viajeCreation.destinoId = null
      viajeCreation.fecha = null
      viajeCreation.guia = null
      viajeCreation.intermediarioId = null
      viajeCreation.kmCargado = null
      viajeCreation.kmVacio = null
      viajeCreation.neto = null
      viajeCreation.notas = null
      viajeCreation.origenId = null
      viajeCreation.valorKm = null
      viajeCreation.vendedorId = null

      viajeCreation.id = null
      viajeCreation.creadorId = null
      viajeCreation.creador = null
      viajeCreation.creada = null
      viajeCreation.modificadorId = null
      viajeCreation.modificador = null
      viajeCreation.modificada = null
      viajeCreation.eliminadorId = null
      viajeCreation.eliminador = null
      viajeCreation.eliminada = null
    }

    function fLimpiarInputs (actual) {
      editAcoplado.value = false
      acoplado.value = null
      editCamion.value = false
      camion.value = null
      editCantidadTransportada.value = false
      cantidadTransportada.value.min = 0
      cantidadTransportada.value.max = 300
      cantidadTransportadaChip.value.izq = false
      cantidadTransportadaChip.value.der = false
      editCategoriaViaje.value = false
      categoriaViaje.value = null
      editComprador.value = false
      comprador.value = null
      editConductor.value = false
      conductor.value = null
      editDireccionCarga.value = false
      direccionCarga.value = null
      editDireccionDestino.value = false
      direccionDestino.value = null
      editDireccionOrigen.value = false
      direccionOrigen.value = null
      editFecha.value = false
      fecha.value.from = null
      fecha.value.to = null
      editIntermediario.value = false
      intermediario.value = null
      editKilometrosCargado.value = false
      kmCargado.value.min = 0
      kmCargado.value.max = 1000
      kmCargadoChip.value.izq = false
      kmCargadoChip.value.der = false
      editKilometrosVacio.value = false
      kmVacio.value.min = 0
      kmVacio.value.max = 1000
      kmVacioChip.value.izq = false
      kmVacioChip.value.der = false
      editNotas.value = false
      notas.value = null
      editNumeroGuia.value = false
      numeroGuia.value = null
      editPesoNeto.value = false
      pesoNeto.value.min = 0
      pesoNeto.value.max = 50000
      pesoNetoChip.value.izq = false
      pesoNetoChip.value.der = false
      editValorKilomertro.value = false
      valorKm.value.min = 0
      valorKm.value.max = 2000
      valorKmChip.value.izq = false
      valorKmChip.value.der = false
      editVendedor.value = false
      vendedor.value = null
    }

    function fMostrarAcoplado () {
      afBuscarAcoplados().then(() => {
        fLimpiarInputs()
        editAcoplado.value = true
      })
    }

    function fMostrarCamion () {
      afBuscarCamiones().then(() => {
        fLimpiarInputs()
        editCamion.value = true
      })
    }

    function fMostrarCantidadTransportada () {
      fLimpiarInputs()
      editCantidadTransportada.value = true
    }

    function fMostrarCategoriaViaje () {
      afBuscarCategoriasViaje().then(() => {
        fLimpiarInputs()
        editCategoriaViaje.value = true
      })
    }

    function fMostrarComprador () {
      afBuscarClientes().then(() => {
        fLimpiarInputs()
        editComprador.value = true
      })
    }

    function fMostrarConductor () {
      afBuscarConductores().then(() => {
        fLimpiarInputs()
        editConductor.value = true
      })
    }

    function fMostrarDireccionCarga () {
      afBuscarDirecciones().then(() => {
        fLimpiarInputs()
        editDireccionCarga.value = true
      })
    }

    function fMostrarDireccionDestino () {
      afBuscarDirecciones().then(() => {
        fLimpiarInputs()
        editDireccionDestino.value = true
      })
    }

    function fMostrarDireccionOrigen () {
      afBuscarDirecciones().then(() => {
        fLimpiarInputs()
        editDireccionOrigen.value = true
      })
    }

    function fMostrarFechaViaje () {
      fLimpiarInputs()
      editFecha.value = true
    }

    function fMostrarIntermediario () {
      afBuscarClientes().then(() => {
        fLimpiarInputs()
        editIntermediario.value = true
      })
    }

    function fMostrarKilometrosCargado () {
      fLimpiarInputs()
      editKilometrosCargado.value = true
    }

    function fMostrarKilometrosVacio () {
      fLimpiarInputs()
      editKilometrosVacio.value = true
    }

    function fMostrarNotas () {
      fLimpiarInputs()
      editNotas.value = true
    }

    function fMostrarEditarFactura (props) {
      titulo.value = 'Editar factura'
      viajeCreation.acopladoId = props.row.acopladoId
      viajeCreation.camionId = props.row.camionId
      viajeCreation.cantidadTransportada = props.row.cantidadTransportada
      viajeCreation.cargaId = props.row.cargaId
      viajeCreation.compradorId = props.row.compradorId
      viajeCreation.categoriaViajeId = props.row.categoriaViajeId
      viajeCreation.conductorId = props.row.conductorId
      viajeCreation.destinoId = props.row.destinoId
      viajeCreation.fechaId = props.row.fechaId

      viajeCreation.fecha = ayuda.fFormatearADatePicker(props.row.fecha.slice(0, 10))
      viajeCreation.guia = props.row.guia
      viajeCreation.intermediarioId = props.row.intermediarioId
      viajeCreation.kmCargado = props.row.kmCargado
      viajeCreation.kmVacio = props.row.kmVacio
      viajeCreation.neto = props.row.neto
      viajeCreation.notas = props.row.notas
      viajeCreation.origenId = props.row.origenId
      viajeCreation.valorKm = props.row.valorKm
      viajeCreation.vendedorId = props.row.vendedorId

      viajeCreation.creada = props.row.creada
      viajeCreation.creadorId = props.row.creadorId
      viajeCreation.eliminada = props.row.eliminada
      viajeCreation.eliminadorId = props.row.eliminadorId
      viajeCreation.modificada = props.row.modificada
      viajeCreation.modificadorId = props.row.modificadorId

      nuevaFacturaDialog.value = true
    }

    function fMostrarEliminarFactura (props) {
      afEliminarViaje(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    function fMostrarFacturarViaje (props) {
      llaveroService.borrarDeLocal('hhFacturarViaje')
      llaveroService.guardarEnLocalConSesion('hhFacturarViaje', props.row)
      console.log(props.row)
      router.push({ name: 'Factura' })
    }

    function fMostrarReciclarFactura (props) {
      afReciclarViaje(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    function fMostrarNuevoFactura () {
      afBuscarConductores().then(() => {
        afBuscarClientes().then(() => {
          afBuscarAcoplados().then(() => {
            afBuscarCamiones().then(() => {
              afBuscarCategoriasViaje().then(() => {
                afBuscarDirecciones().then(() => {
                  titulo.value = 'Nuevo factura'
                  fLimpiarFormulario()
                  fIrPaso1()
                  nuevaFacturaDialog.value = true
                })
              })
            })
          })
        })
      })
    }

    function fMostrarNumeroGuia () {
      fLimpiarInputs()
      editNumeroGuia.value = true
    }

    function fMostrarPesoNeto () {
      fLimpiarInputs()
      editPesoNeto.value = true
    }

    function fMostrarTotal (datos) {
      return ((datos.kmCargado * datos.valorKm) + datos.recarga - datos.descuento) * (datos.iva / 100)
    }

    function fMostrarValorKilomertro () {
      fLimpiarInputs()
      editValorKilomertro.value = true
    }

    function fMostrarVendedor () {
      afBuscarClientes().then(() => {
        fLimpiarInputs()
        editVendedor.value = true
      })
    }

    return {
      step: ref(1),

      afBuscarPorAcopladoId,
      afBuscarPorCamionId,
      afBuscarPorCantidadTransportada,
      afBuscarPorCategoriaViajeId,
      afBuscarPorConductorId,
      afBuscarPorCompradorId,
      afBuscarPorDireccionCargaId,
      afBuscarPorDireccionDestinoId,
      afBuscarPorDireccionOrigenId,
      afBuscarPorFechaViaje,
      afBuscarPorIntermediarioId,
      afBuscarPorKilometrosCargado,
      afBuscarPorKilometrosVacio,
      afBuscarPorNotas,
      afBuscarPorGuia,
      afBuscarPorPesoNeto,
      afBuscarPorValorKm,
      afBuscarPorVendedorId,

      editAcoplado,
      acoplado,
      acoplados,
      editCamion,
      camion,
      camiones,
      editCantidadTransportada,
      cantidadTransportada,
      cantidadTransportadaChip,
      editCategoriaViaje,
      categoriaViaje,
      categoriasViaje,
      editComprador,
      comprador,
      compradores,
      editConductor,
      conductor,
      conductores,
      editDireccionCarga,
      direccionCarga,
      direccionesCarga,
      editDireccionDestino,
      direccionDestino,
      direccionesDestino,
      editDireccionOrigen,
      direccionOrigen,
      direccionesOrigen,
      editFecha,
      fecha,
      editIntermediario,
      intermediario,
      intermediarios,
      editKilometrosCargado,
      kmCargado,
      kmCargadoChip,
      editKilometrosVacio,
      kmVacio,
      kmVacioChip,
      editNotas,
      notas,
      editNumeroGuia,
      numeroGuia,
      numerosGuia,
      editPesoNeto,
      pesoNeto,
      pesoNetoChip,
      editValorKilomertro,
      valorKm,
      valorKmChip,
      editVendedor,
      vendedor,
      vendedores,

      columnas,
      esAdmin,
      nuevaBusqueda,
      paginacion,
      reglas,
      titulo,
      facturas,

      fIrPaso2,
      fIrPaso3,
      fGuardarViaje,
      fMostrarNuevoFactura,
      fMostrarEditarFactura,
      fMostrarEliminarFactura,
      fMostrarFacturarViaje,
      fMostrarReciclarFactura,
      fMostrarTotal,
      nuevaFacturaDialog,
      paso1,
      paso2,
      paso3,
      viajeCreation,

      fMostrarAcoplado,
      fMostrarCamion,
      fMostrarCantidadTransportada,
      fMostrarCategoriaViaje,
      fMostrarComprador,
      fMostrarConductor,
      fMostrarDireccionCarga,
      fMostrarDireccionDestino,
      fMostrarDireccionOrigen,
      fMostrarFechaViaje,
      fMostrarIntermediario,
      fMostrarKilometrosCargado,
      fMostrarKilometrosVacio,
      fMostrarNotas,
      fMostrarNumeroGuia,
      fMostrarPesoNeto,
      fMostrarValorKilomertro,
      fMostrarVendedor,

      fFiltrarAcoplados,
      fFiltrarCamiones,
      fFiltrarCategoriasViaje,
      fFiltrarConductores,
      fFiltrarCompradores,
      fFiltrarDireccionesCarga,
      fFiltrarDireccionesDestino,
      fFiltrarDireccionesOrigen,
      fFiltrarIntermediarios,
      fFiltrarVendedores,
      fFormatoFecha,

      myLocale: {
        /* starting with Sunday */
        days: 'Domingo_Lunes_Martes_Miércoles_Jueves_Viernes_Sábado'.split('_'),
        daysShort: 'Dom_Lun_Mar_Mié_Jue_Vie_Sáb'.split('_'),
        months: 'Enero_Febrero_Marzo_Abril_Mayo_Junio_Julio_Agosto_Septiembre_Octubre_Noviembre_Diciembre'.split(
          '_'
        ),
        monthsShort: 'Ene_Feb_Mar_Abr_May_Jun_Jul_Ago_Sep_Oct_Nov_Dic'.split('_'),
        firstDayOfWeek: 1, // 0-6, 0 - Sunday, 1 Monday, ...
        format24h: true,
        pluralDay: 'dias'
      }
    }
  }
}
</script>
<style scoped>
.edits-fondo {
  background: #9e9e9e;
}
.edits {
  color: #9e9e9e;
}
.q-btn-dropdown {
  width: 250px;
}
.q-select,
.q-range {
  width: 300px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
.nuevo-input {
  width: 250px;
}
</style>
