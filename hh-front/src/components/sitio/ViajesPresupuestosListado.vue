<template>
  <q-card class="q-ma-md font-5 no-shadow no-border">
    <q-card-section>
      <div class="row justify-center items-center">
        <div class="col-md-4 q-mb-md">
          <q-btn class="paleta2-fondo2 paleta1-color1" icon-right="add_circle" label="Nuevo viaje"  @click="nuevoPresupuestoDialog = true"> </q-btn>
        </div>
        <div class="col-md-4 q-mb-md q-mx-md">
          <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar presupuestos por" dropdown-icon="fa-solid fa-magnifying-glass">
            <q-list>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCategoriaViaje">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-boxes-packing" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Categoria viaje</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarComprador">
                <q-item-section avatar>
                  <q-icon name="monetization_on" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Comprador</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarDireccionCarga">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-truck-ramp-box" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Dirección carga</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarDireccionDestino">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-map-location" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Dirección destino</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarDireccionOrigen">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-map-location-dot" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Dirección origen</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarFechaViaje">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-calendar-days" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Fecha viaje</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarKilometrosCargado">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-truck-fast" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Kilometros cargado</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarKilometrosVacio">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-truck-pickup" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Kilometros vacio</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNotas">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-pen-to-square" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Notas</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarValorKilomertro">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-money-bill-1-wave" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Valor kilometro</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarVendedor">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-cash-register" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Vendedor</q-item-label>
                </q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </div>
        <div class="col-md-4">
          <q-select
            v-if="editCategoriaViaje"
            outlined
            dense
            emit-value
            map-options
            clearable
            v-model="categoriaViaje"
            option-value="id"
            option-label="categoria"
            label="Buscar por categoria de viaje"
            use-input
            hide-selected
            fill-input
            :options="categoriasViaje"
            @filter="fFiltrarCategoriasViaje"
            @update:model-value="afBuscarPorCategoriaViajeId()"
            hint="Tenés que escribir al menos 3 caracteres para buscar."
          >
            <template v-slot:before>
              <q-icon name="fa-solid fa-boxes-packing" class="q-mx-xs" />
            </template>
            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey"> Sin resultados </q-item-section>
              </q-item>
            </template>
          </q-select>
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
          <q-select
            v-if="editDireccionCarga"
            outlined
            dense
            emit-value
            map-options
            clearable
            v-model="direccionCarga"
            :options="direccionesCarga"
            option-value="id"
            option-label="direccion"
            label="Buscar por carga"
            use-input
            input-debounce="0"
            @filter="fFiltrarDireccionesCarga"
            @update:model-value="afBuscarPorDireccionCargaId()"
            hint="Tenés que escribir al menos 3 caracteres para buscar."
          >
            <template v-slot:before>
              <q-icon name="fa-solid fa-truck-ramp-box" class="q-mx-xs" />
            </template>
            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey"> Sin resultados </q-item-section>
              </q-item>
            </template>
          </q-select>
          <q-select
            v-if="editDireccionDestino"
            outlined
            dense
            emit-value
            map-options
            clearable
            v-model="direccionDestino"
            :options="direccionesDestino"
            option-value="id"
            option-label="direccion"
            label="Buscar por destino"
            use-input
            input-debounce="0"
            @filter="fFiltrarDireccionesDestino"
            @update:model-value="afBuscarPorDireccionDestinoId()"
            hint="Tenés que escribir al menos 3 caracteres para buscar."
          >
            <template v-slot:before>
              <q-icon name="fa-solid fa-map-location" class="q-mx-xs" />
            </template>
            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey"> Sin resultados </q-item-section>
              </q-item>
            </template>
          </q-select>
          <q-select
            v-if="editDireccionOrigen"
            outlined
            dense
            emit-value
            map-options
            clearable
            v-model="direccionOrigen"
            :options="direccionesOrigen"
            option-value="id"
            option-label="direccion"
            label="Buscar por origen"
            use-input
            input-debounce="0"
            @filter="fFiltrarDireccionesOrigen"
            @update:model-value="afBuscarPorDireccionOrigenId()"
            hint="Tenés que escribir al menos 3 caracteres para buscar."
          >
            <template v-slot:before>
              <q-icon name="fa-solid fa-map-location-dot" class="q-mx-xs" />
            </template>
            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey"> Sin resultados </q-item-section>
              </q-item>
            </template>
          </q-select>
          <div class="column" v-if="editFecha">
            <div class="row justify-around">

                <q-input
                  mask="##-##-####"
                  style="width: 180px"
                  v-model="fecha.from"
                  outlined
                  dense
                  clearable
                  label="Fecha fin"
                  hint="20-01-2020"
                >
                  <template v-slot:before>
                    <q-icon name="event" class="cursor-pointer">
                      <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                        <q-date v-model="fecha.from" mask="DD-MM-YYYY">
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
                  v-model="fecha.to"
                  outlined
                  dense
                  clearable
                  label="Fecha inicio"
                  hint="30-01-2020"
                >
                  <template v-slot:before>
                    <q-icon name="event" class="cursor-pointer">
                      <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                        <q-date v-model="fecha.to" mask="DD-MM-YYYY">
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
          <div class="column" v-if="editKilometrosCargado">
            <div class="row justify-between">
              <div class="col">
                <q-chip
                  v-model:selected="kmCargadoChip.izq"
                  :class="{ 'paleta2-fondo2': kmCargadoChip.izq, 'edits-fondo': !kmCargadoChip.izq  }"
                  text-color="white"
                  size="12px"
                  icon="fa-solid fa-minus"
                  @update:selected="afBuscarPorKilometrosCargado()"
                >
                  Mínimo
                </q-chip>
              </div>
              <div class="edits col text-center">
                <span>Kms cargado</span><br />
                <q-icon name="fa-solid fa-truck-fast" />
              </div>
              <div class="col text-right">
                <q-chip
                  v-model:selected="kmCargadoChip.der"
                  :class="{ 'paleta2-fondo2': kmCargadoChip.der, 'edits-fondo': !kmCargadoChip.der  }"
                  text-color="white"
                  size="12px"
                  icon="fa-solid fa-plus"
                  @update:selected="afBuscarPorKilometrosCargado()"
                >
                  Máximo
                </q-chip>
              </div>
            </div>
            <div class="row">
              <q-range
                label-always
                switch-label-side
                color="grey-6"
                v-model="kmCargado"
                :min="1"
                :max="1000"
                label
                @change="kmCargadoChip.izq = false, kmCargadoChip.der = false"
              />
            </div>
          </div>
          <div class="column" v-if="editKilometrosVacio">
            <div class="row justify-between">
              <div class="col">
                <q-chip
                  v-model:selected="kmVacioChip.izq"
                  :class="{ 'paleta2-fondo2': kmVacioChip.izq, 'edits-fondo': !kmVacioChip.izq  }"
                  text-color="white"
                  size="12px"
                  icon="fa-solid fa-minus"
                  @update:selected="afBuscarPorKilometrosVacio()"
                >
                  Mínimo
                </q-chip>
              </div>
              <div class="edits col text-center">
                <span>Kms vacio</span><br />
                <q-icon name="fa-solid fa-truck-pickup" />
              </div>
              <div class="col text-right">
                <q-chip
                  v-model:selected="kmVacioChip.der"
                  :class="{ 'paleta2-fondo2': kmVacioChip.der, 'edits-fondo': !kmVacioChip.der  }"
                  text-color="white"
                  size="12px"
                  icon="fa-solid fa-plus"
                  @update:selected="afBuscarPorKilometrosVacio()"
                >
                  Máximo
                </q-chip>
              </div>
            </div>
            <div class="row">
              <q-range
                label-always
                switch-label-side
                color="grey-6"
                v-model="kmVacio"
                :min="1"
                :max="1000"
                label
                @change="kmVacioChip.izq = false, kmVacioChip.der = false"
              />
            </div>
          </div>
          <q-input
            v-if="editNotas"
            outlined
            dense
            clearable
            v-on:keyup.enter="afBuscarPorNotas()"
            v-model="notas"
            label="Buscar por notas"
            hint="Tenés que escribir al menos 3 caracteres para buscar."
          >
            <template v-slot:before>
              <q-icon name="fa-solid fa-cash-register" class="q-mx-xs" />
            </template>
            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey"> Sin resultados </q-item-section>
              </q-item>
            </template>
            <template v-slot:after>
              <q-icon name="fa-solid fa-magnifying-glass" class="q-mx-xs" v-on:click="afBuscarPorNotas()" style="cursor: pointer" />
            </template>
          </q-input>
          <div class="column" v-if="editValorKilomertro">
            <div class="row justify-between">
              <div class="col">
                <q-chip
                  v-model:selected="valorKmChip.izq"
                  :class="{ 'paleta2-fondo2': valorKmChip.izq, 'edits-fondo': !valorKmChip.izq  }"
                  text-color="white"
                  size="12px"
                  icon="fa-solid fa-minus"
                  @update:selected="afBuscarPorValorKm()"
                >
                  Mínimo
                </q-chip>
              </div>
              <div class="edits col text-center">
                <span>Valor kilometro</span><br />
                <q-icon name="fa-solid fa-money-bill-1-wave" />
              </div>
              <div class="col text-right">
                <q-chip
                  v-model:selected="valorKmChip.der"
                  :class="{ 'paleta2-fondo2': valorKmChip.der, 'edits-fondo': !valorKmChip.der  }"
                  text-color="white"
                  size="12px"
                  icon="fa-solid fa-plus"
                  @update:selected="afBuscarPorValorKm()"
                >
                  Máximo
                </q-chip>
              </div>
            </div>
            <div class="row">
              <q-range
                label-always
                switch-label-side
                color="grey-6"
                v-model="valorKm"
                :min="0"
                :max="2000"
                label
                @change="valorKmChip.izq = false, valorKmChip.der = false"
              />
            </div>
          </div>
          <q-select
            v-if="editVendedor"
            outlined
            dense
            emit-value
            map-options
            clearable
            v-model="vendedor"
            :options="vendedores"
            option-value="id"
            option-label="nombre"
            label="Buscar por vendedor"
            use-input
            input-debounce="0"
            @filter="fFiltrarVendedores"
            @update:model-value="afBuscarPorVendedorId()"
            hint="Tenés que escribir al menos 3 caracteres para buscar."
          >
            <template v-slot:before>
              <q-icon name="fa-solid fa-cash-register" class="q-mx-xs" />
            </template>
            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey"> Sin resultados </q-item-section>
              </q-item>
            </template>
          </q-select>
        </div>
      </div>
    </q-card-section>
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        title="Presupuestos"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        hide-no-data
        :rows="presupuestos"
        row-key="id"
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td auto-width class="text-center">
              <q-btn
                size="sm"
                class="text-white"
                :class="props.expand ? 'paleta5-fondo2' : 'paleta5-fondo3'"
                round
                dense
                @click="props.expand = !props.expand"
                :icon="props.expand ? 'remove' : 'add'"
              />
            </q-td>
            <q-td class="text-center">
              {{ props.row.id }}
            </q-td>
            <q-td>
              {{ props.row.comprador}}
            </q-td>
            <q-td>
              {{ props.row.categoriaViaje }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.cantidadTransportada }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.valorKm }}
            </q-td>
          </q-tr>
          <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
            <q-td colspan="100%">
              <div class="row">
                <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.id }}</div>
                  <div class="row paleta1-color2">Id</div>
                </div>
                <div v-if="props.row.categoriaViaje != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.categoriaViaje }}</div>
                  <div class="row paleta1-color2">Categoria viaje</div>
                </div>
                <div v-if="props.row.cantidadTransportada != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                  <div class="row text-white">{{ props.row.cantidadTransportada }}</div>
                  <div class="row paleta1-color2">Cantidad transportada</div>
                </div>
                <div v-if="props.row.carga != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.carga }}</div>
                  <div class="row paleta1-color2">Direccion carga</div>
                </div>
                <div v-if="props.row.destino != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                  <div class="row text-white">{{ props.row.destino }}</div>
                  <div class="row paleta1-color2">Direccion de destino</div>
                </div>
                <div v-if="props.row.origen != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.origen }}</div>
                  <div class="row paleta1-color2">Direccion origen</div>
                </div>
                <div v-if="props.row.fecha != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.fecha }}</div>
                  <div class="row paleta1-color2">Fecha del viaje</div>
                </div>
                <div v-if="props.row.kmCargado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.kmCargado }}</div>
                  <div class="row paleta1-color2">Kilometros cargado</div>
                </div>
                <div v-if="props.row.kmVacio != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.kmVacio }}</div>
                  <div class="row paleta1-color2">Kilometros vacio</div>
                </div>
                <div v-if="props.row.notas != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.notas }}</div>
                  <div class="row paleta1-color2">Notas</div>
                </div>
                <div v-if="props.row.comprador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.comprador }}</div>
                  <div class="row paleta1-color2">Nombre comprador</div>
                </div>
                <div v-if="props.row.vendedor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.vendedor }}</div>
                  <div class="row paleta1-color2">Nombre vendedor</div>
                </div>
                <div v-if="props.row.valorKm != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.valorKm }}</div>
                  <div class="row paleta1-color2">Valor del kilometro</div>
                </div>
                <div v-if="props.row.creador != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.creador }}</div>
                  <div class="row paleta1-color2">Creador</div>
                </div>
                <div v-if="props.row.creado != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.creado }}</div>
                  <div class="row paleta1-color2">Creado</div>
                </div>
                <div v-if="props.row.modificador != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.modificador }}</div>
                  <div class="row paleta1-color2">Modificador</div>
                </div>
                <div v-if="props.row.modificado != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.modificado }}</div>
                  <div class="row paleta1-color2">Modificado</div>
                </div>
                <div v-if="props.row.eliminador != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                  <div class="row text-white">{{ props.row.eliminador }}</div>
                  <div class="row paleta1-color2">Eliminador</div>
                </div>
                <div v-if="props.row.eliminado != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.elimiando }}</div>
                  <div class="row paleta1-color2">Elimiando</div>
                </div>
              </div>
            </q-td>
          </q-tr>
        </template>
      </q-table>
    </div>
  </div>
    <q-dialog v-model="nuevoPresupuestoDialog" persistent transition-show="flip-down" transition-hide="flip-up">
      <q-card class="bg-primary text-white">
        <q-bar>
          <q-icon name="network_wifi" />
          <q-icon name="network_cell" />
          <q-icon name="battery_full" />
          <div>9:34</div>

          <q-space />

          <q-btn dense flat icon="close" v-close-popup>
            <q-tooltip class="bg-white text-primary">Close</q-tooltip>
          </q-btn>
        </q-bar>

        <q-card-section>
          <div class="text-h6">Alert</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Rerum repellendus sit voluptate voluptas eveniet porro. Rerum blanditiis perferendis totam, ea at omnis vel numquam exercitationem aut, natus minima, porro labore.
        </q-card-section>
      </q-card>
    </q-dialog>
</template>

<script>
import { ref, reactive, computed } from 'vue'
import { rolEnum } from 'src/models/enums/rol_enum'
import { useQuasar } from 'quasar'
import { v4 as uuidv4 } from 'uuid'

import { ayuda } from 'app/src/helpers/ayuda'
import { llaveroService } from 'src/helpers/llavero_service'

import { autenticacionService } from 'src/services/autenticacion_service'
import { categoriaViajeService } from 'src/services/categoria_viaje_service'
import { clienteService } from 'src/services/cliente_service'
import { direccionService } from 'src/services/direccion_service'
import { notificarService } from 'src/helpers/notificar_service'
import { presupuestoService } from 'src/services/presupuesto_service'
import { PresupuestoModel } from 'src/models/presupuesto_model'

const paginacion = {
  rowsPerPage: 50,
  sortBy: 'id',
  descending: true
}

const columnas = [
  {
    label: 'Expandir',
    align: 'center'
  },
  {
    name: 'id',
    label: 'Id',
    align: 'center',
    field: 'id',
    sortOrder: 'da',
    sortable: true
  },
  {
    name: 'comprador',
    label: 'Comprador',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'vendedor',
    label: 'Vendedor',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'categoria',
    label: 'Categoria',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'cantidadTransportada',
    label: 'Cantidad',
    align: 'center',
    field: 'cantidadTransportada'
  },
  {
    name: 'kmVacio',
    label: 'Km Vacio',
    align: 'center',
    field: 'kmVacio'
  },
  {
    name: 'kmCargado',
    label: 'Km Cargado',
    align: 'center',
    field: 'kmCargado'
  },
  {
    name: 'valorKm',
    label: 'Valor Km',
    align: 'center',
    field: 'valorKm'
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const sesion = ref(uuidv4())

    const editCategoriaViaje = ref(false)
    const editComprador = ref(true)
    const editDireccionCarga = ref(false)
    const editDireccionDestino = ref(false)
    const editDireccionOrigen = ref(false)
    const editFecha = ref(false)
    const editKilometrosCargado = ref(false)
    const editKilometrosVacio = ref(false)
    const editNotas = ref(false)
    const editValorKilomertro = ref(false)
    const editVendedor = ref(false)
    const nuevoPresupuestoDialog = ref(false)

    const esAdmin = ref(autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN))
    const categoriaViaje = ref(null)
    const categoriasViaje = ref([])
    const categoriasViajeList = ref([])
    const comprador = ref(null)
    const compradores = ref([])
    const direccionCarga = ref(null)
    const direccionesCarga = ref([])
    const direccionDestino = ref(null)
    const direccionesDestino = ref([])
    const direccionOrigen = ref(null)
    const direccionesOrigen = ref([])
    const direccionesList = ref([])
    const fecha = ref({ from: null, to: null })
    const fechas = ref([])
    const kmCargado = ref({ min: 0, max: 1000 })
    const kmCargadoChip = ref({ izq: false, der: false })
    const kmsCargado = ref([])
    const kmVacio = ref({ min: 0, max: 1000 })
    const kmVacioChip = ref({ izq: false, der: false })
    const kmsVacio = ref([])
    const notas = ref(null)
    const notases = ref([])
    const valorKm = ref({ min: 0, max: 2000 })
    const valorKmChip = ref({ izq: false, der: false })
    const valoresKm = ref([])
    const vendedor = ref(null)
    const vendedores = ref([])

    const clientesList = ref([])
    const nuevaBusqueda = ref(false)
    const presupuesto = reactive(new PresupuestoModel())
    const presupuestos = ref([])

    afBuscarPaginadas()

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

    async function afBuscarDirecciones () {
      $q.loading.show()
      try {
        let resultado = null
        if (esAdmin.value) {
          if (llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConEliminadasConSesion', sesion.value) !== null) {
            direccionesList.value = llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConEliminadasConSesion', sesion.value).value
            console.log('DireccionService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await direccionService.spfBuscarTodasConEliminadasConSesion(
              sesion.value
            )
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
          resultado = await presupuestoService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await presupuestoService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          presupuestos.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          presupuestos.value = []
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

    async function afBuscarPorCategoriaViajeId () {
      if (categoriaViaje.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await presupuestoService.spfBuscarTodasPorCategoriaViajeIdConEliminadas(categoriaViaje.value)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorCategoriaViajeId(categoriaViaje.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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
            resultado = await presupuestoService.spfBuscarTodasPorCompradorIdConEliminadas(comprador.value)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorCompradorId(comprador.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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
            resultado = await presupuestoService.spfBuscarTodasPorDireccionCargaIdConEliminadas(direccionCarga.value)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorDireccionCargaId(direccionCarga.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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
            resultado = await presupuestoService.spfBuscarTodasPorDireccionDestinoIdConEliminadas(direccionDestino.value)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorDireccionDestinoId(direccionDestino.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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
            resultado = await presupuestoService.spfBuscarTodasPorDireccionOrigenIdConEliminadas(direccionOrigen.value)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorDireccionOrigenId(direccionOrigen.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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
            resultado = await presupuestoService.spfBuscarTodasPorFechaViajeEntreFechasConEliminadas(
              ayuda.fFormatearDeDatePicker(fecha.value.from),
              ayuda.fFormatearDeDatePicker(fecha.value.to)
            )
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorFechaViajeEntreFechas(
              ayuda.fFormatearDeDatePicker(fecha.value.from),
              ayuda.fFormatearDeDatePicker(fecha.value.to)
            )
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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
            resultado = await presupuestoService.spfBuscarTodasPorRangoKmCargadoConEliminadas(kmCargado.value.min, kmCargado.value.max)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorRangoKmCargado(kmCargado.value.min, kmCargado.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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
            resultado = await presupuestoService.spfBuscarTodasPorRangoKmVacioConEliminadas(kmVacio.value.min, kmVacio.value.max)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorRangoKmVacio(kmVacio.value.min, kmVacio.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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
            resultado = await presupuestoService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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

    async function afBuscarPorValorKm () {
      if (valorKmChip.value.izq === true && valorKmChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await presupuestoService.spfBuscarTodasPorRangoValorKmConEliminadas(valorKm.value.min, valorKm.value.max)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorRangoValorKm(valorKm.value.min, valorKm.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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
            resultado = await presupuestoService.spfBuscarTodasPorVendedorIdConEliminadas(vendedor.value)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorVendedorId(vendedor.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            presupuestos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            presupuestos.value = []
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

    function fFiltrarDireccionesCarga (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        direccionesCarga.value = direccionesList.value.filter(
          (v) => v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1
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
          (v) => v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1
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
          (v) => v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1
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

    function fLimpiarInputs (actual) {
      editCategoriaViaje.value = false
      categoriaViaje.value = null
      editComprador.value = false
      comprador.value = null
      editDireccionCarga.value = false
      direccionCarga.value = null
      editDireccionDestino.value = false
      direccionDestino.value = null
      editDireccionOrigen.value = false
      direccionOrigen.value = null
      editFecha.value = false
      fecha.value.from = null
      fecha.value.to = null
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
      editValorKilomertro.value = false
      valorKm.value.min = 0
      valorKm.value.max = 2000
      valorKmChip.value.izq = false
      valorKmChip.value.der = false
      editVendedor.value = false
      vendedor.value = null
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
      afBuscarPorCategoriaViajeId,
      afBuscarPorCompradorId,
      afBuscarPorDireccionCargaId,
      afBuscarPorDireccionDestinoId,
      afBuscarPorDireccionOrigenId,
      afBuscarPorFechaViaje,
      afBuscarPorKilometrosCargado,
      afBuscarPorKilometrosVacio,
      afBuscarPorNotas,
      afBuscarPorValorKm,
      afBuscarPorVendedorId,

      editCategoriaViaje,
      categoriaViaje,
      categoriasViaje,
      editComprador,
      comprador,
      compradores,
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
      fechas,
      editKilometrosCargado,
      kmCargado,
      kmCargadoChip,
      kmsCargado,
      editKilometrosVacio,
      kmVacio,
      kmVacioChip,
      kmsVacio,
      editNotas,
      notas,
      notases,
      editValorKilomertro,
      valorKm,
      valorKmChip,
      valoresKm,
      editVendedor,
      vendedor,
      vendedores,

      columnas,
      esAdmin,
      nuevaBusqueda,
      nuevoPresupuestoDialog,
      paginacion,
      presupuesto,
      presupuestos,

      fMostrarCategoriaViaje,
      fMostrarComprador,
      fMostrarDireccionCarga,
      fMostrarDireccionDestino,
      fMostrarDireccionOrigen,
      fMostrarFechaViaje,
      fMostrarKilometrosCargado,
      fMostrarKilometrosVacio,
      fMostrarNotas,
      fMostrarValorKilomertro,
      fMostrarVendedor,

      fFiltrarCategoriasViaje,
      fFiltrarCompradores,
      fFiltrarDireccionesCarga,
      fFiltrarDireccionesDestino,
      fFiltrarDireccionesOrigen,
      fFiltrarVendedores,
      fFormatoFecha
    }
  }
}
</script>
<style scoped>
.edits-fondo {
  background: #9E9E9E;
}
.edits {
  color: #9E9E9E;
}
.q-btn-dropdown {
  width: 280px;
}
.q-select, .q-range {
  width: 300px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
</style>
