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
        <!--template v-slot:top-left>
          <div class="column">
            <p class="text-h5">Facturas</p>
            <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo factura" @click="fMostrarNuevoFactura" />
          </div>
        </!template-->
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
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="facturaCreation.cantidadTransportada"
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
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="facturaCreation.compradorId"
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
                v-model="facturaCreation.categoriaViajeId"
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
                v-model="facturaCreation.cantidadTransportada"
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
                v-model="facturaCreation.vendedorId"
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
                v-model="facturaCreation.intermediarioId"
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
                v-model="facturaCreation.conductorId"
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
                v-model="facturaCreation.camionId"
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
                v-model="facturaCreation.acopladoId"
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
                v-model="facturaCreation.cargaId"
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
                v-model="facturaCreation.origenId"
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
                v-model="facturaCreation.destinoId"
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
        <q-form v-on:submit.prevent="fGuardarFactura">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##############"
                v-model.number="facturaCreation.neto"
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
                v-model="facturaCreation.guia"
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
                v-model.number="facturaCreation.kmVacio"
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
                v-model.number="facturaCreation.kmCargado"
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
                v-model.number="facturaCreation.valorKm"
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
                v-model="facturaCreation.notas"
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
import { autenticacionService } from 'src/services/autenticacion_service'
import { ayuda } from 'app/src/helpers/ayuda'
import { FacturaCreation } from 'src/models/creation/factura_creation'
import { facturaService } from 'src/services/factura_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { onMounted, reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { rolEnum } from 'src/models/enums/rol_enum'
import { useQuasar } from 'quasar'

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
    const esAdmin = ref(autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN))
    const reglas = reactive(reglasValidacion.reglas)
    const sesion = ayuda.getUid()

    const editComprador = ref(true)
    const comprador = ref(null)
    const editFechaFacturacion = ref(false)
    const fechaFacturacion = ref({ from: null, to: null })
    const editFechaViaje = ref(false)
    const fechaViaje = ref({ from: null, to: null })
    const editNumeroComprobante = ref(false)
    const numeroComprobante = ref(null)
    const editNumeroGuia = ref(false)
    const numeroGuia = ref(null)
    const editNumeroRemito = ref(false)
    const numeroRemito = ref(null)
    const editTipoComprobante = ref(false)
    const tipoComprobante = ref(null)

    const facturaCreation = reactive(new FacturaCreation())
    const facturas = ref([])
    const nuevaFacturaDialog = ref(false)
    const paso1 = ref(true)
    const paso2 = ref(false)
    const paso3 = ref(false)
    const titulo = ref(null)

    afBuscarPaginadas()

    onMounted(() => {
      if (llaveroService.obtenerDeLocal('hhFacturarViaje') !== null) {
        const resultado = llaveroService.obtenerDeLocal('hhFacturarViaje')
        facturaCreation.viajeId = resultado.value.id
        facturaCreation.razonSocial = resultado.value.comprador
        facturaCreation.domicilioComercial = resultado.value.direccion
        facturaCreation.identificacion = resultado.value.identificacion
        facturaCreation.tipoComprobante = null
        facturaCreation.numeroComprobante = null
        facturaCreation.fechaEmision = null
        facturaCreation.fechaVencimiento = null
        facturaCreation.codigo = null
        facturaCreation.concepto = 'Transporte de ' + resultado.value.cantidadTransportada + ' ' + resultado.value.categoriaViaje
        facturaCreation.cantidad = resultado.value.kmCargado
        facturaCreation.precioUnitario = resultado.value.valorKm
        facturaCreation.condicionPagoEnum = null
        facturaCreation.otrosImpuestos = null
        facturaCreation.iva = null
        facturaCreation.pagada = null
        facturaCreation.notas = null

        titulo.value = 'Facturar viaje de ' + resultado.value.comprador
        fIrPaso1()
        llaveroService.borrarDeLocal('hhFacturarViaje')
        nuevaFacturaDialog.value = true
      }
    })

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
          resultado = await facturaService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await facturaService.spfBuscarTodasPaginadas(paginadoDTO)
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

    async function afBuscarPorCompradorId (id) {
      if (comprador.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await facturaService.spfBuscarTodasPorCompradorIdConEliminadas(comprador.value)
          } else {
            resultado = await facturaService.spfBuscarTodasPorCompradorId(comprador.value)
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
            resultado = await facturaService.spfBuscarTodasPorGuiaConEliminadas(numeroGuia.value)
          } else {
            resultado = await facturaService.spfBuscarTodasPorGuia(numeroGuia.value)
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

    async function afGuardarFactura () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await facturaService.spfGuardar(facturaCreation)
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

    async function afEliminarFactura (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await facturaService.spfBorrar(id)
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

    async function afReciclarFactura (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await facturaService.spfReciclar(id)
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

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fGuardarFactura () {
      afGuardarFactura().then(() => {
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
      facturaCreation.id = null
      facturaCreation.creadorId = null
      facturaCreation.creador = null
      facturaCreation.creada = null
      facturaCreation.modificadorId = null
      facturaCreation.modificador = null
      facturaCreation.modificada = null
      facturaCreation.eliminadorId = null
      facturaCreation.eliminador = null
      facturaCreation.eliminada = null
    }

    function fLimpiarInputs (actual) {
      editComprador.value = false
      editFechaFacturacion.value = false
      editFechaViaje.value = false
      editNumeroComprobante.value = false
      editNumeroGuia.value = false
      editNumeroRemito.value = false
      editTipoComprobante.value = false

      comprador.value = null
      fechaFacturacion.value.from = null
      fechaFacturacion.value.to = null
      fechaViaje.value.from = null
      fechaViaje.value.to = null
      numeroComprobante.value = null
      numeroGuia.value = null
      editNumeroRemito.value = null
      tipoComprobante.value = null
    }

    function fMostrarEditarFactura (props) {
      titulo.value = 'Editar factura'
      facturaCreation.acopladoId = props.row.acopladoId
      facturaCreation.camionId = props.row.camionId
      facturaCreation.cantidadTransportada = props.row.cantidadTransportada
      facturaCreation.cargaId = props.row.cargaId
      facturaCreation.compradorId = props.row.compradorId
      facturaCreation.categoriaViajeId = props.row.categoriaViajeId
      facturaCreation.conductorId = props.row.conductorId
      facturaCreation.destinoId = props.row.destinoId
      facturaCreation.fechaId = props.row.fechaId

      facturaCreation.fecha = ayuda.fFormatearADatePicker(props.row.fecha.slice(0, 10))
      facturaCreation.guia = props.row.guia
      facturaCreation.intermediarioId = props.row.intermediarioId
      facturaCreation.kmCargado = props.row.kmCargado
      facturaCreation.kmVacio = props.row.kmVacio
      facturaCreation.neto = props.row.neto
      facturaCreation.notas = props.row.notas
      facturaCreation.origenId = props.row.origenId
      facturaCreation.valorKm = props.row.valorKm
      facturaCreation.vendedorId = props.row.vendedorId

      facturaCreation.creada = props.row.creada
      facturaCreation.creadorId = props.row.creadorId
      facturaCreation.eliminada = props.row.eliminada
      facturaCreation.eliminadorId = props.row.eliminadorId
      facturaCreation.modificada = props.row.modificada
      facturaCreation.modificadorId = props.row.modificadorId

      nuevaFacturaDialog.value = true
    }

    function fMostrarEliminarFactura (props) {
      afEliminarFactura(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    function fMostrarReciclarFactura (props) {
      afReciclarFactura(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    function fMostrarNumeroGuia () {
      fLimpiarInputs()
      editNumeroGuia.value = true
    }

    function fMostrarTotal (datos) {
      return ((datos.kmCargado * datos.valorKm) + datos.recarga - datos.descuento) * (datos.iva / 100)
    }

    return {
      afBuscarPorCompradorId,

      columnas,
      esAdmin,
      paginacion,
      reglas,
      titulo,
      facturas,

      fIrPaso2,
      fIrPaso3,
      fGuardarFactura,
      fMostrarEditarFactura,
      fMostrarEliminarFactura,
      paso3,
      facturaCreation,

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
