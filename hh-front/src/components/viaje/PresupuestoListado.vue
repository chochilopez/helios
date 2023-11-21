<template>
    <q-card class="font-5 no-shadow no-border">
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
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Presupuestos</p>
              <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo presupuesto" @click="fMostrarNuevoPresupuesto" />
            </div>
          </template>
          <template v-slot:top-right>
            <div class="column items-end">
              <div class="q-my-md">
                <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar presupuestos por" dropdown-icon="fa-solid fa-magnifying-glass">
                  <q-list>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCantidadTransportada">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-cubes-stacked" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Cantidad transportada</q-item-label>
                      </q-item-section>
                    </q-item>
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
                        <q-item-label>Fecha emision</q-item-label>
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
                  </q-list>
                </q-btn-dropdown>
              </div>
              <div class="col">
                <div class="column" v-if="editCantidadTransportada">
                  <div class="row justify-between">
                    <div class="col">
                      <q-chip
                        v-model:selected="cantidadTransportadaChip.izq"
                        :class="{ 'paleta2-fondo2': cantidadTransportadaChip.izq, 'edits-fondo': !cantidadTransportadaChip.izq }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-minus"
                        @update:selected="afBuscarPorCantidadTransportada()"
                      >
                        Mínimo
                      </q-chip>
                    </div>
                    <div class="edits col text-center">
                      <span>Cantidad transportada</span><br />
                      <q-icon name="fa-solid fa-cubes-stacked" />
                    </div>
                    <div class="col text-right">
                      <q-chip
                        v-model:selected="cantidadTransportadaChip.der"
                        :class="{ 'paleta2-fondo2': cantidadTransportadaChip.der, 'edits-fondo': !cantidadTransportadaChip.der }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-plus"
                        @update:selected="afBuscarPorCantidadTransportada()"
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
                      v-model="cantidadTransportada"
                      :min="1"
                      :max="300"
                      label
                      @change="(cantidadTransportadaChip.izq = false), (cantidadTransportadaChip.der = false)"
                    />
                  </div>
                </div>
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
              </div>
            </div>
          </template>
          <template v-slot:body="props">
            <q-tr :props="props" :class="(props.row.eliminada === null) ? '':'bg-red-2'">
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
                  @click="fMostrarEditarPresupuesto(props)"
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
                  @click="fMostrarConfirmarPresupuesto(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="done" />
                  <q-tooltip>
                    Confirmar presupuesto
                  </q-tooltip>
                </q-btn>
                <q-btn
                  v-if="props.row.eliminada === null"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarEliminarPresupuesto(props)"
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
                  @click="fMostrarReciclarPresupuesto(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
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
              <q-td>
                {{ props.row.destino }}
              </q-td>
              <q-td class="text-center">
                {{ fFormatoFecha(props.row.fecha) }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.kmCargado }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.valorKm }}
              </q-td>
              <q-td class="text-center">
                {{ (props.row.valorKm * props.row.kmCargado).toFixed(2) }}
              </q-td>
            </q-tr>
            <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
              <q-td colspan="100%">
                <div class="row">
                  <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.id }}</div>
                    <div class="row paleta1-color2">Id</div>
                  </div>
                  <div v-if="props.row.cantidadTransportada != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.cantidadTransportada }}</div>
                    <div class="row paleta1-color2">Cantidad transportada</div>
                  </div>
                  <div v-if="props.row.categoriaViaje != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.categoriaViaje }}</div>
                    <div class="row paleta1-color2">Categoria viaje</div>
                  </div>
                  <div v-if="props.row.comprador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.comprador }}</div>
                    <div class="row paleta1-color2">Comprador</div>
                  </div>
                  <div v-if="props.row.destino != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.destino }}</div>
                    <div class="row paleta1-color2">Destino</div>
                  </div>
                  <div v-if="props.row.fecha != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ fFormatoFecha(props.row.fecha) }}</div>
                    <div class="row paleta1-color2">Fecha viaje</div>
                  </div>
                  <div v-if="props.row.fecha != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ fFormatoFecha(props.row.fechaEmision) }}</div>
                    <div class="row paleta1-color2">Fecha emisión</div>
                  </div>
                  <div v-if="props.row.kmCargado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.kmCargado }}</div>
                    <div class="row paleta1-color2">Kilometro cargado</div>
                  </div>
                  <div v-if="props.row.origen != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.origen }}</div>
                    <div class="row paleta1-color2">Direccion origen</div>
                  </div>
                  <div v-if="props.row.validez != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.validez }}</div>
                    <div class="row paleta1-color2">Validez presupuesto</div>
                  </div>
                  <div v-if="props.row.valorKm != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.valorKm }}</div>
                    <div class="row paleta1-color2">Valor kilometro</div>
                  </div>
                  <div v-if="props.row.valorKm != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ (props.row.valorKm * props.row.kmCargado).toFixed(2) }}</div>
                    <div class="row paleta1-color2">Total</div>
                  </div>
                  <div v-if="props.row.creador != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.creador }}</div>
                    <div class="row paleta1-color2">Creador</div>
                  </div>
                  <div v-if="props.row.creada != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.creada }}</div>
                    <div class="row paleta1-color2">Creado</div>
                  </div>
                  <div v-if="props.row.modificador != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.modificador }}</div>
                    <div class="row paleta1-color2">Modificador</div>
                  </div>
                  <div v-if="props.row.modificada != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.modificada }}</div>
                    <div class="row paleta1-color2">Modificado</div>
                  </div>
                  <div v-if="props.row.eliminador != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.eliminador }}</div>
                    <div class="row paleta1-color2">Eliminador</div>
                  </div>
                  <div v-if="props.row.eliminada != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.Eeiminada }}</div>
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
  </q-card>

  <q-dialog v-model="nuevoPresupuestoDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nuevo presupuesto</div>
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
                v-model="presupuestoCreation.fecha"
                :rules="[reglas.requerido,]"
                outlined
                dense
                readonly
                clearable
                label="Fecha del viaje"
                hint="Formato dd-mm-yyyy."
              >
                <template v-slot:prepend>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="presupuestoCreation.fecha" mask="DD-MM-YYYY" :locale="myLocale">
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
                v-model="presupuestoCreation.compradorId"
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
                v-model="presupuestoCreation.categoriaViajeId"
                :rules="[reglas.requerido]"
                option-value="id"
                option-label="categoria"
                label="Categoria de viaje"
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
                v-model="presupuestoCreation.cantidadTransportada"
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
          <div class="row justify-end q-pa-md">
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="fa-solid fa-arrow-right" ripple >
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
                v-model="presupuestoCreation.origenId"
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
                v-model="presupuestoCreation.destinoId"
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
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##############"
                v-model.number="presupuestoCreation.kmCargado"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Kilometros cargado"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model.number="presupuestoCreation.valorKm"
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
          </div>
          <div class="row justify-between q-pa-md">
            <q-btn class="paleta2-color2 bg-white" @click="fIrPaso1()" icon="fa-solid fa-arrow-left" ripple>
              Atras
            </q-btn>
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="fa-solid fa-arrow-right" ripple>
              Siguiente
            </q-btn>

          </div>
        </q-form>
      </q-card-section>

      <q-card-section v-if="paso3">
        <q-form v-on:submit.prevent="fGuardarPresupuesto">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model.number="presupuestoCreation.validez"
                :rules="[reglas.requerido]"
                :max-decimals="2"
                type="number"
                outlined
                dense
                clearable
                label="Validez en días"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="presupuestoCreation.notas"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              />
            </div>
          </div>
          <div class="row justify-between q-pa-md">
            <q-btn class="paleta2-color2 bg-white" @click="fIrPaso2()" icon="fa-solid fa-arrow-left" ripple>
              Atras
            </q-btn><q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="save" ripple >
              Finalizar
            </q-btn>
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ayuda } from 'app/src/helpers/ayuda'
import { categoriaViajeService } from 'src/services/categoria_viaje_service'
import { clienteService } from 'src/services/cliente_service'
import { direccionService } from 'src/services/direccion_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { presupuestoService } from 'src/services/presupuesto_service'
import { PresupuestoCreation } from 'src/models/creation/presupuesto_creation'
import { reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { useQuasar } from 'quasar'
import { useRouter } from 'vue-router'

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
    name: 'comprador',
    label: 'Comprador',
    align: 'left',
    field: 'comprador',
    sortable: true
  },
  {
    name: 'categoriaViaje',
    label: 'Categría viaje',
    align: 'left',
    field: 'categoriaViaje',
    sortable: true
  },
  {
    name: 'cantidadTransportada',
    label: 'Cantidad transportada',
    align: 'center',
    field: 'cantidadTransportada',
    sortable: true
  },
  {
    name: 'destino',
    label: 'Destino',
    align: 'left',
    field: ''
  },
  {
    name: 'fecha',
    label: 'Fecha viaje',
    align: 'left',
    field: ''
  },
  {
    name: 'kmCargado',
    label: 'Kilometro cargado',
    align: 'center',
    field: ''
  },
  {
    name: 'valorKm',
    label: 'Valor Km',
    align: 'center',
    field: 'valorKm',
    sortable: true
  },
  {
    name: 'total',
    label: 'Total',
    align: 'center',
    field: '',
    sortable: true
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const router = useRouter()
    const autoridad = ref(ayuda.getAutoridad())

    const cantidadTransportada = ref({ min: 0, max: 300 })
    const cantidadTransportadaChip = ref({ izq: false, der: false })
    const categoriaViaje = ref(null)
    const categoriasViaje = ref([])
    const categoriasViajeList = ref([])
    const clientesList = ref([])
    const comprador = ref(null)
    const compradores = ref([])
    const direccionDestino = ref(null)
    const direccionesDestino = ref([])
    const direccionOrigen = ref(null)
    const direccionesOrigen = ref([])
    const direccionesList = ref([])
    const editCantidadTransportada = ref(false)
    const editCategoriaViaje = ref(false)
    const editComprador = ref(true)
    const editDireccionDestino = ref(false)
    const editDireccionOrigen = ref(false)
    const editFecha = ref(false)
    const editKilometrosCargado = ref(false)
    const editNotas = ref(false)
    const editValorKilomertro = ref(false)
    const fecha = ref({ from: null, to: null })
    const kmCargado = ref({ min: 0, max: 1000 })
    const kmCargadoChip = ref({ izq: false, der: false })
    const notas = ref(null)
    const nuevaBusqueda = ref(false)
    const nuevoPresupuestoDialog = ref(false)
    const paso1 = ref(true)
    const paso2 = ref(false)
    const paso3 = ref(false)
    const presupuestos = ref([])
    const presupuestoCreation = reactive(new PresupuestoCreation())
    const reglas = reactive(reglasValidacion.reglas)
    const sesion = ref(ayuda.getUid())
    const valorKm = ref({ min: 0, max: 2000 })
    const valorKmChip = ref({ izq: false, der: false })

    afBuscarPaginadas()

    async function afBuscarCategoriasViaje () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
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
        if (autoridad.value === 'admin') {
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
        if (autoridad.value === 'admin') {
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
        if (autoridad.value === 'admin') {
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

    async function afBuscarPorCantidadTransportada () {
      if (cantidadTransportadaChip.value.izq === true && cantidadTransportadaChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await presupuestoService.spfBuscarTodasPorRangoCantidadTransportadaConEliminadas(cantidadTransportada.value.min, cantidadTransportada.value.max)
          } else {
            resultado = await presupuestoService.spfBuscarTodasPorRangoCantidadTransportada(cantidadTransportada.value.min, cantidadTransportada.value.max)
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
        cantidadTransportadaChip.value.izq = false
        cantidadTransportadaChip.value.der = false
      }
    }

    async function afBuscarPorCategoriaViajeId () {
      if (categoriaViaje.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
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
          if (autoridad.value === 'admin') {
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

    async function afBuscarPorDireccionDestinoId () {
      if (direccionDestino.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
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
          if (autoridad.value === 'admin') {
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
          if (autoridad.value === 'admin') {
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
          if (autoridad.value === 'admin') {
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

    async function afBuscarPorNotas () {
      if (notas.value !== null && notas.value.length > 2) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
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
          if (autoridad.value === 'admin') {
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

    async function afGuardarPresupuesto () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await presupuestoService.spfGuardar(presupuestoCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el prespuesto.')
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

    async function afEliminarPresupuesto (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await presupuestoService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el prespuesto.')
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

    async function afReciclarPresupuesto (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await presupuestoService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el prespuesto.')
        }
      } catch (err) {
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

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fGuardarPresupuesto () {
      afGuardarPresupuesto().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoPresupuestoDialog.value = false
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
      paso3.value = false
    }

    function fIrPaso3 () {
      paso1.value = false
      paso2.value = false
      paso3.value = true
    }

    function fLimpiarFormulario () {
      presupuestoCreation.cantidadTransportada = null
      presupuestoCreation.categoriaViajeId = null
      presupuestoCreation.compradorId = null
      presupuestoCreation.destinoId = null
      presupuestoCreation.fecha = null
      presupuestoCreation.kmCargado = null
      presupuestoCreation.notas = null
      presupuestoCreation.origenId = null
      presupuestoCreation.validez = null
      presupuestoCreation.valorKm = null

      presupuestoCreation.id = null
      presupuestoCreation.creadorId = null
      presupuestoCreation.creador = null
      presupuestoCreation.creada = null
      presupuestoCreation.modificadorId = null
      presupuestoCreation.modificador = null
      presupuestoCreation.modificada = null
      presupuestoCreation.eliminadorId = null
      presupuestoCreation.eliminador = null
      presupuestoCreation.eliminada = null
    }

    function fLimpiarInputs (actual) {
      editCantidadTransportada.value = false
      cantidadTransportada.value.min = 0
      cantidadTransportada.value.max = 1000
      cantidadTransportadaChip.value.izq = false
      cantidadTransportadaChip.value.der = false
      editCategoriaViaje.value = false
      categoriaViaje.value = null
      editComprador.value = false
      comprador.value = null
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
      editNotas.value = false
      notas.value = null
      editValorKilomertro.value = false
      valorKm.value.min = 0
      valorKm.value.max = 2000
      valorKmChip.value.izq = false
      valorKmChip.value.der = false
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

    function fMostrarNotas () {
      fLimpiarInputs()
      editNotas.value = true
    }

    function fMostrarValorKilomertro () {
      fLimpiarInputs()
      editValorKilomertro.value = true
    }

    function fMostrarConfirmarPresupuesto (props) {
      llaveroService.borrarDeLocal('hhConfirmarPresupuesto')
      llaveroService.guardarEnLocalConSesion('hhConfirmarPresupuesto', props.row)
      router.push({ name: 'Viaje' })
    }

    function fMostrarNuevoPresupuesto () {
      afBuscarClientes().then(() => {
        afBuscarCategoriasViaje().then(() => {
          afBuscarDirecciones().then(() => {
            fIrPaso1()
            nuevoPresupuestoDialog.value = true
          })
        })
      })
    }

    function fMostrarEditarPresupuesto (props) {
      presupuestoCreation.id = props.row.id
      presupuestoCreation.cantidadTransportada = props.row.cantidadTransportada
      presupuestoCreation.categoriaViajeId = props.row.categoriaViajeId
      presupuestoCreation.compradorId = props.row.compradorId
      presupuestoCreation.destinoId = props.row.destinoId
      presupuestoCreation.fecha = ayuda.fFormatearADatePicker(props.row.fecha.slice(0, 10))
      presupuestoCreation.kmCargado = props.row.kmCargado
      presupuestoCreation.notas = props.row.notas
      presupuestoCreation.origenId = props.row.origenId
      presupuestoCreation.validez = props.row.validez
      presupuestoCreation.valorKm = props.row.valorKm

      presupuestoCreation.creada = props.row.creada
      presupuestoCreation.creadorId = props.row.creadorId
      presupuestoCreation.eliminada = props.row.eliminada
      presupuestoCreation.eliminadorId = props.row.eliminadorId
      presupuestoCreation.modificada = props.row.modificada
      presupuestoCreation.modificadorId = props.row.modificadorId

      nuevoPresupuestoDialog.value = true
    }

    function fMostrarEliminarPresupuesto (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarPresupuesto(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarPresupuesto (props) {
      afReciclarPresupuesto(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      autoridad,
      afBuscarPorCantidadTransportada,
      afBuscarPorCategoriaViajeId,
      afBuscarPorCompradorId,
      afBuscarPorDireccionDestinoId,
      afBuscarPorDireccionOrigenId,
      afBuscarPorFechaViaje,
      afBuscarPorKilometrosCargado,
      afBuscarPorNotas,
      afBuscarPorValorKm,
      comprador,
      compradores,
      cantidadTransportada,
      cantidadTransportadaChip,
      categoriaViaje,
      categoriasViaje,
      columnas,
      direccionDestino,
      direccionesDestino,
      direccionOrigen,
      direccionesOrigen,
      editCantidadTransportada,
      editCategoriaViaje,
      editComprador,
      editDireccionDestino,
      editDireccionOrigen,
      editFecha,
      editKilometrosCargado,
      editNotas,
      editValorKilomertro,
      fecha,
      fFiltrarCategoriasViaje,
      fFiltrarCompradores,
      fFiltrarDireccionesDestino,
      fFiltrarDireccionesOrigen,
      fFormatoFecha,
      fGuardarPresupuesto,
      fIrPaso1,
      fIrPaso2,
      fIrPaso3,
      fMostrarCantidadTransportada,
      fMostrarCategoriaViaje,
      fMostrarComprador,
      fMostrarDireccionDestino,
      fMostrarDireccionOrigen,
      fMostrarFechaViaje,
      fMostrarKilometrosCargado,
      fMostrarNotas,
      fMostrarConfirmarPresupuesto,
      fMostrarNuevoPresupuesto,
      fMostrarEditarPresupuesto,
      fMostrarEliminarPresupuesto,
      fMostrarReciclarPresupuesto,
      fMostrarValorKilomertro,
      kmCargado,
      kmCargadoChip,
      notas,
      nuevaBusqueda,
      nuevoPresupuestoDialog,
      paginacion,
      paso1,
      paso2,
      paso3,
      presupuestoCreation,
      presupuestos,
      reglas,
      valorKm,
      valorKmChip,

      myLocale: {
        /* starting with Sunday */
        days: 'Domingo_Lunes_Martes_Miércoles_Jueves_Viernes_Sábado'.split('_'),
        daysShort: 'Dom_Lun_Mar_Mié_Jue_Vie_Sáb'.split('_'),
        months: 'Enero_Febrero_Marzo_Abril_Mayo_Junio_Julio_Agosto_Septiembre_Octubre_Noviembre_Diciembre'.split('_'),
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
  width: 290px;
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
