<template>
  <q-card class="font-5 no-shadow no-border"> </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table title="Viajes"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        hide-no-data
        :rows="viajes"
        row-key="id"
      >
        <template v-slot:top-left>
          <div class="column">
            <p class="text-h5">Viajes</p>
            <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo viaje" @click="fMostrarNuevoViaje" />
          </div>
        </template>
        <template v-slot:top-right>
          <div class="column items-end">
            <div class="q-my-md">
              <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar viajes por" dropdown-icon="fa-solid fa-magnifying-glass">
                <q-list>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarAcoplado">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-trailer" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Acoplado</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCamion">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-truck-fast" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Camion</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCantidadTransportada">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-cubes-stacked" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Cantidad transportada</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCategoriaViaje">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-boxes-packing" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Categoria viaje</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarComprador">
                    <q-item-section avatar>
                      <q-icon name="monetization_on" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Comprador</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarConductor">
                    <q-item-section avatar>
                      <q-icon name="airline_seat_recline_extra" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Conductor</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarDireccionCarga">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-truck-ramp-box" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Dirección carga</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarDireccionDestino">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-map-location" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Dirección destino</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarDireccionOrigen">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-map-location-dot" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Dirección origen</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarFechaViaje">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-calendar-days" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Fecha viaje</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarIntermediario">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-people-arrows" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Intermediario</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarKilometrosCargado">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-truck-fast" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Kilometros cargado</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarKilometrosVacio">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-truck-pickup" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Kilometros vacio</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNotas">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-pen-to-square" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Notas</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNumeroGuia">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-shuffle" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Numero guía</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarPesoNeto">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-plus-minus" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Peso neto</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarValorKilomertro">
                    <q-item-section avatar>
                      <q-icon name="fa-solid fa-money-bill-1-wave" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Valor kilometro</q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarVendedor">
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
                v-if="editAcoplado"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="acoplado"
                option-value="id"
                option-label="modelo"
                label="Buscar por modelo acoplado"
                use-input
                hide-selected
                fill-input
                :options="acoplados"
                @filter="fFiltrarAcoplados"
                @update:model-value="afBuscarPorAcopladoId()"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
              <q-select
                v-if="editCamion"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="camion"
                option-value="id"
                option-label="modelo"
                label="Buscar por modelo camion"
                use-input
                hide-selected
                fill-input
                :options="camiones"
                @filter="fFiltrarCamiones"
                @update:model-value="afBuscarPorCamionId()"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
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
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
              <q-select
                v-if="editConductor"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="conductor"
                :options="conductores"
                option-value="id"
                option-label="nombre"
                label="Buscar por conductor"
                use-input
                input-debounce="0"
                @filter="fFiltrarConductores"
                @update:model-value="afBuscarPorConductorId()"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
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
                    <q-icon
                      name="fa-solid fa-magnifying-glass"
                      size="24px"
                      class="cursor-pointer q-pa-sm edits"
                      v-on:click="afBuscarPorFechaViaje()"
                    />
                  </div>
                </div>
              </div>
              <q-select
                v-if="editIntermediario"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="intermediario"
                :options="intermediarios"
                option-value="id"
                option-label="nombre"
                label="Buscar por intermediario"
                use-input
                input-debounce="0"
                @filter="fFiltrarIntermediarios"
                @update:model-value="afBuscarPorIntermediarioId()"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
              <div class="column" v-if="editKilometrosCargado">
                <div class="row justify-between">
                  <div class="col">
                    <q-chip
                      v-model:selected="kmCargadoChip.izq"
                      :class="{ 'paleta2-fondo2': kmCargadoChip.izq, 'edits-fondo': !kmCargadoChip.izq }"
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
                      :class="{ 'paleta2-fondo2': kmCargadoChip.der, 'edits-fondo': !kmCargadoChip.der }"
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
                    @change="(kmCargadoChip.izq = false), (kmCargadoChip.der = false)"
                  />
                </div>
              </div>
              <div class="column" v-if="editKilometrosVacio">
                <div class="row justify-between">
                  <div class="col">
                    <q-chip
                      v-model:selected="kmVacioChip.izq"
                      :class="{ 'paleta2-fondo2': kmVacioChip.izq, 'edits-fondo': !kmVacioChip.izq }"
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
                      :class="{ 'paleta2-fondo2': kmVacioChip.der, 'edits-fondo': !kmVacioChip.der }"
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
                    @change="(kmVacioChip.izq = false), (kmVacioChip.der = false)"
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
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
                <template v-slot:after>
                  <q-icon
                    name="fa-solid fa-magnifying-glass"
                    class="q-mx-xs"
                    v-on:click="afBuscarPorNotas()"
                    style="cursor: pointer"
                  />
                </template>
              </q-input>
              <q-input
                v-if="editNumeroGuia"
                outlined
                dense
                clearable
                v-on:keyup.enter="afBuscarPorGuia()"
                v-model="numeroGuia"
                label="Buscar por número guía"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
                <template v-slot:after>
                  <q-icon
                    name="fa-solid fa-magnifying-glass"
                    class="q-mx-xs"
                    v-on:click="afBuscarPorGuia()"
                    style="cursor: pointer"
                  />
                </template>
              </q-input>
              <div class="column" v-if="editPesoNeto">
                <div class="row justify-between">
                  <div class="col">
                    <q-chip
                      v-model:selected="pesoNetoChip.izq"
                      :class="{ 'paleta2-fondo2': pesoNetoChip.izq, 'edits-fondo': !pesoNetoChip.izq }"
                      text-color="white"
                      size="12px"
                      icon="fa-solid fa-minus"
                      @update:selected="afBuscarPorPesoNeto()"
                    >
                      Mínimo
                    </q-chip>
                  </div>
                  <div class="edits col text-center">
                    <span>Peso Neto</span><br />
                    <q-icon name="fa-solid fa-plus-minus" />
                  </div>
                  <div class="col text-right">
                    <q-chip
                      v-model:selected="pesoNetoChip.der"
                      :class="{ 'paleta2-fondo2': pesoNetoChip.der, 'edits-fondo': !pesoNetoChip.der }"
                      text-color="white"
                      size="12px"
                      icon="fa-solid fa-plus"
                      @update:selected="afBuscarPorPesoNeto()"
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
                    v-model="pesoNeto"
                    :min="1"
                    :max="50000"
                    label
                    @change="(pesoNetoChip.izq = false), (pesoNetoChip.der = false)"
                  />
                </div>
              </div>
              <div class="column" v-if="editValorKilomertro">
                <div class="row justify-between">
                  <div class="col">
                    <q-chip
                      v-model:selected="valorKmChip.izq"
                      :class="{ 'paleta2-fondo2': valorKmChip.izq, 'edits-fondo': !valorKmChip.izq }"
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
                      :class="{ 'paleta2-fondo2': valorKmChip.der, 'edits-fondo': !valorKmChip.der }"
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
                    @change="(valorKmChip.izq = false), (valorKmChip.der = false)"
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
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
        </template>
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
              {{ fFormatoFecha(props.row.fecha) }}
            </q-td>
            <q-td>
              {{ props.row.conductor }}
            </q-td>
            <q-td>
              {{ props.row.camion }}
            </q-td>
            <q-td>
              {{ props.row.comprador }}
            </q-td>
            <q-td>
              {{ props.row.categoriaViaje }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.cantidadTransportada }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.kmVacio }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.kmCargado }}
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
                <div v-if="props.row.acoplado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.acoplado }}</div>
                  <div class="row paleta1-color2">Acoplado</div>
                </div>
                <div v-if="props.row.camion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.camion }}</div>
                  <div class="row paleta1-color2">Camion</div>
                </div>
                <div v-if="props.row.categoriaViaje != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.categoriaViaje }}</div>
                  <div class="row paleta1-color2">Categoria viaje</div>
                </div>
                <div v-if="props.row.cantidadTransportada != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.cantidadTransportada }}</div>
                  <div class="row paleta1-color2">Cantidad transportada</div>
                </div>
                <div v-if="props.row.carga != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.carga }}</div>
                  <div class="row paleta1-color2">Direccion carga</div>
                </div>
                <div v-if="props.row.destino != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
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
                <div v-if="props.row.guia != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.guia }}</div>
                  <div class="row paleta1-color2">N° guia</div>
                </div>
                <div v-if="props.row.comprador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.comprador }}</div>
                  <div class="row paleta1-color2">Nombre comprador</div>
                </div>
                <div v-if="props.row.conductor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.conductor }}</div>
                  <div class="row paleta1-color2">Nombre conductor</div>
                </div>
                <div v-if="props.row.intermediario != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.intermediario }}</div>
                  <div class="row paleta1-color2">Nombre intermediario</div>
                </div>
                <div v-if="props.row.vendedor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.vendedor }}</div>
                  <div class="row paleta1-color2">Nombre vendedor</div>
                </div>
                <div v-if="props.row.neto != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                  <div class="row text-white">{{ props.row.neto }}</div>
                  <div class="row paleta1-color2">Peso neto</div>
                </div>
                <div v-if="props.row.valorKm != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.valorKm }}</div>
                  <div class="row paleta1-color2">Valor del kilometro</div>
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
                  <div class="row text-white">{{ props.row.elimiando }}</div>
                  <div class="row paleta1-color2">Elimiando</div>
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

  <q-dialog v-model="nuevoViajeDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nuevo viaje</div>
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
            <div class="col-xs-5 q-mx-xs q-my-md">
              <q-input
                class="nuevo-input"
                mask="##-##-####"
                v-model="viajeCreation.fecha"
                :rules="[reglas.requerido]"
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
          <div class="row justify-end q-mr-xl q-my-md">
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="arrow_right_alt" ripple>
              Siguiente
            </q-btn>
          </div>
        </q-form>
      </q-card-section>

      <q-card-section v-if="paso2">
        <q-form v-on:submit.prevent="fIrPaso3">
          <div class="row justify-around">
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
                option-label="modelo"
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
                option-label="modelo"
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
          <div class="row justify-end q-mr-xl q-my-md">
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="arrow_right_alt" ripple>
              Siguiente
            </q-btn>
          </div>
        </q-form>
      </q-card-section>

      <q-card-section v-if="paso3">
        <q-form v-on:submit.prevent="fGuardarViaje">
          <div class="row justify-around">
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
              <q-input
                class="nuevo-input"
                v-model="viajeCreation.guia"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Número de guía"
              >
              </q-input>
            </div>
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
            <div class="col-xs-5 q-mx-xs q-my-md">
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
          <div class="row justify-end q-mr-xl q-my-md">
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
import { reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { rolEnum } from 'src/models/enums/rol_enum'
import { useQuasar } from 'quasar'
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
    name: 'fecha',
    label: 'Fecha',
    align: 'left',
    field: 'fecha',
    sortOrder: 'da',
    sortable: true
  },
  {
    name: 'conductor',
    label: 'Conductor',
    align: 'left',
    field: 'conductor',
    sortable: true
  },
  {
    name: 'camion',
    label: 'Camion',
    align: 'left',
    field: ''
  },
  {
    name: 'comprador',
    label: 'Comprador',
    align: 'left',
    field: 'comprador',
    sortable: true
  },
  {
    name: 'categoria',
    label: 'Categoria',
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
    name: 'kmVacio',
    label: 'Km Vacio',
    align: 'center',
    field: ''
  },
  {
    name: 'kmCargado',
    label: 'Km Cargado',
    align: 'center',
    field: ''
  },
  {
    name: 'valorKm',
    label: 'Valor Km',
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
    const nuevoViajeDialog = ref(false)

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
    const viajes = ref([])

    afBuscarPaginadas()

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
          viajes.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          viajes.value = []
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
            viajes.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
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
          notificarService.notificarExito('Se creó correctamente el viaje.')
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
          (v) => v.modelo.toLowerCase().indexOf(val.toLowerCase()) > -1
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
          (v) => v.modelo.toLowerCase().indexOf(val.toLowerCase()) > -1
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
          nuevoViajeDialog.value = false
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

    function fMostrarNuevoViaje () {
      afBuscarConductores().then(() => {
        afBuscarClientes().then(() => {
          afBuscarAcoplados().then(() => {
            afBuscarCamiones().then(() => {
              afBuscarCategoriasViaje().then(() => {
                afBuscarDirecciones().then(() => {
                  fLimpiarFormulario()
                  fIrPaso1()
                  nuevoViajeDialog.value = true
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
      viajes,

      fIrPaso2,
      fIrPaso3,
      fGuardarViaje,
      fMostrarNuevoViaje,
      nuevoViajeDialog,
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
